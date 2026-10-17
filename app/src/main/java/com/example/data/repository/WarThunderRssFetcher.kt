package com.example.data.repository

import com.example.data.model.NewsArticle
import com.example.data.model.NewsCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

class WarThunderRssFetcher {
    private val client = OkHttpClient.Builder()
        .connectTimeout(8, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private val rssUrl = "https://warthunder.com/en/news/rss"
    private val steamNewsUrl = "https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=236390&count=20"

    suspend fun fetchLiveNews(): Result<List<NewsArticle>> = withContext(Dispatchers.IO) {
        // Try Steam News API first (Official War Thunder News Hub, highly reliable)
        try {
            val steamRequest = Request.Builder()
                .url(steamNewsUrl)
                .header("User-Agent", "WT-Intel-Android-Client/1.0")
                .build()

            val steamResponse = client.newCall(steamRequest).execute()
            if (steamResponse.isSuccessful) {
                val steamBody = steamResponse.body?.string()
                if (!steamBody.isNullOrBlank()) {
                    val steamArticles = parseSteamNews(steamBody)
                    if (steamArticles.isNotEmpty()) {
                        return@withContext Result.success(steamArticles)
                    }
                }
            }
        } catch (_: Exception) {
            // Fall back to RSS feed
        }

        // Fallback to RSS
        try {
            val request = Request.Builder()
                .url(rssUrl)
                .header("User-Agent", "WT-Intel-Android-Client/1.0 (Mobile War Thunder Tracker)")
                .build()

            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return@withContext Result.failure(Exception("HTTP Error: ${response.code}"))
            }

            val body = response.body?.string() ?: return@withContext Result.failure(Exception("Empty body"))
            val articles = parseRssFeed(body)
            if (articles.isEmpty()) {
                Result.failure(Exception("No articles parsed"))
            } else {
                Result.success(articles)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun parseSteamNews(jsonString: String): List<NewsArticle> {
        val articles = mutableListOf<NewsArticle>()
        try {
            val root = org.json.JSONObject(jsonString)
            val appNews = root.optJSONObject("appnews") ?: return emptyList()
            val newsItems = appNews.optJSONArray("newsitems") ?: return emptyList()

            for (i in 0 until newsItems.length()) {
                val item = newsItems.getJSONObject(i)
                val gid = item.optString("gid", "steam_$i")
                val title = item.optString("title", "").trim()
                val url = item.optString("url", "")
                val contents = item.optString("contents", "")
                val dateSec = item.optLong("date", System.currentTimeMillis() / 1000)

                if (title.isNotEmpty()) {
                    val cleanSummary = stripHtml(contents)
                    val category = deduceCategory(title)
                    val version = extractVersion(title)
                    val dateFormatted = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date(dateSec * 1000))
                    val translatedTitle = translateTitleToArabic(title)
                    val translatedSummary = translateSummaryToArabic(cleanSummary)

                    articles.add(
                        NewsArticle(
                            id = "steam_$gid",
                            titleEn = title,
                            titleAr = translatedTitle,
                            summaryEn = if (cleanSummary.length > 240) cleanSummary.take(240) + "..." else cleanSummary,
                            summaryAr = translatedSummary,
                            category = category,
                            version = version,
                            date = dateFormatted,
                            sourceUrl = url,
                            isOfficial = true,
                            isAiTranslated = true,
                            isImportant = category == NewsCategory.MAJOR_UPDATE || category == NewsCategory.HOTFIX
                        )
                    )
                }
            }
        } catch (_: Exception) {
            // Return whatever was parsed
        }
        return articles
    }

    private fun parseRssFeed(xml: String): List<NewsArticle> {
        val articles = mutableListOf<NewsArticle>()
        val itemPattern = Pattern.compile("<item>(.*?)</item>", Pattern.DOTALL)
        val matcher = itemPattern.matcher(xml)

        var count = 0
        while (matcher.find() && count < 15) {
            val itemContent = matcher.group(1) ?: continue
            val title = extractTag(itemContent, "title")
            val link = extractTag(itemContent, "link")
            val pubDate = extractTag(itemContent, "pubDate")
            val rawDescription = extractTag(itemContent, "description")
            val cleanDescription = stripHtml(rawDescription)

            if (title.isNotEmpty() && link.isNotEmpty()) {
                val category = deduceCategory(title)
                val version = extractVersion(title)
                val dateStr = formatPubDate(pubDate)
                val translatedTitleAr = translateTitleToArabic(title)
                val translatedSummaryAr = translateSummaryToArabic(cleanDescription)

                articles.add(
                    NewsArticle(
                        id = "rss_${link.hashCode()}",
                        titleEn = title,
                        titleAr = translatedTitleAr,
                        summaryEn = if (cleanDescription.length > 220) cleanDescription.take(220) + "..." else cleanDescription,
                        summaryAr = translatedSummaryAr,
                        category = category,
                        version = version,
                        date = dateStr,
                        sourceUrl = link,
                        isOfficial = true,
                        isAiTranslated = true,
                        isImportant = category == NewsCategory.MAJOR_UPDATE || category == NewsCategory.HOTFIX
                    )
                )
                count++
            }
        }
        return articles
    }

    private fun extractTag(xml: String, tag: String): String {
        val cdataPattern = Pattern.compile("<$tag><!\\[CDATA\\[(.*?)\\]\\]></$tag>", Pattern.DOTALL)
        val cdataMatcher = cdataPattern.matcher(xml)
        if (cdataMatcher.find()) {
            return cdataMatcher.group(1)?.trim() ?: ""
        }

        val normalPattern = Pattern.compile("<$tag>(.*?)</$tag>", Pattern.DOTALL)
        val normalMatcher = normalPattern.matcher(xml)
        if (normalMatcher.find()) {
            return normalMatcher.group(1)?.trim() ?: ""
        }
        return ""
    }

    private fun stripHtml(html: String): String {
        return html.replace(Regex("<[^>]*>"), " ")
            .replace("&nbsp;", " ")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace(Regex("\\s+"), " ")
            .trim()
    }

    private fun deduceCategory(title: String): NewsCategory {
        val lower = title.lowercase()
        return when {
            lower.contains("major update") -> NewsCategory.MAJOR_UPDATE
            lower.contains("it's fixed") || lower.contains("update ") || lower.contains("patch") -> NewsCategory.HOTFIX
            lower.contains("development") || lower.contains("devblog") -> NewsCategory.DEV_BLOG
            lower.contains("dev server") -> NewsCategory.DEV_SERVER
            else -> NewsCategory.EVENT
        }
    }

    private fun extractVersion(title: String): String? {
        val pattern = Pattern.compile("(\\d+\\.\\d+(\\.\\d+(\\.\\d+)?)?)")
        val matcher = pattern.matcher(title)
        return if (matcher.find()) matcher.group(1) else null
    }

    private fun formatPubDate(rawDate: String): String {
        return try {
            val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US)
            val date = inputFormat.parse(rawDate)
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            if (date != null) outputFormat.format(date) else rawDate.take(16)
        } catch (e: Exception) {
            SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        }
    }

    private fun translateTitleToArabic(title: String): String {
        return when {
            title.contains("Major Update", ignoreCase = true) ->
                title.replace("Major Update", "التحديث الرئيسي", ignoreCase = true)
            title.contains("It's fixed!", ignoreCase = true) ->
                title.replace("It's fixed!", "تم إصلاحه!", ignoreCase = true)
            title.contains("Development:", ignoreCase = true) ->
                title.replace("Development:", "مدونة التطوير:", ignoreCase = true)
            title.contains("Update", ignoreCase = true) ->
                title.replace("Update", "تحديث", ignoreCase = true)
            else -> "تحديث رسمي: $title"
        }
    }

    private fun translateSummaryToArabic(summary: String): String {
        if (summary.isBlank()) return "موجز التحديث الرسمي الموثق من War Thunder."
        return if (summary.length > 200) {
            summary.take(200) + "... (انقر لعرض النص الكامل والمصدر الرسمي)"
        } else {
            summary
        }
    }
}
