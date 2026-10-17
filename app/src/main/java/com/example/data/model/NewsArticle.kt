package com.example.data.model

enum class NewsCategory(val nameEn: String, val nameAr: String) {
    MAJOR_UPDATE("Major Update", "تحديث رئيسي"),
    HOTFIX("Patch / Hotfix", "تصحيح / تحديث فرعي"),
    DEV_BLOG("Development Blog", "مدونة التطوير"),
    DEV_SERVER("Dev Server", "خادم التطوير"),
    EVENT("Special Event", "حدث خاص / مناورة")
}

data class NewsArticle(
    val id: String,
    val titleEn: String,
    val titleAr: String,
    val summaryEn: String,
    val summaryAr: String,
    val category: NewsCategory,
    val version: String?,
    val date: String,
    val sourceUrl: String,
    val isOfficial: Boolean = true,
    val isAiTranslated: Boolean = true,
    val isImportant: Boolean = false,
    val affectedVehicles: List<String> = emptyList()
) {
    fun localizedTitle(language: AppLanguage): String =
        if (language == AppLanguage.AR) titleAr else titleEn

    fun localizedSummary(language: AppLanguage): String =
        if (language == AppLanguage.AR) summaryAr else summaryEn
}

data class NotificationItem(
    val id: String,
    val titleEn: String,
    val titleAr: String,
    val messageEn: String,
    val messageAr: String,
    val timestamp: String,
    val type: ChangeType,
    val vehicleId: String? = null,
    val isRead: Boolean = false,
    val sourceUrl: String = "https://warthunder.com/en/news/"
) {
    fun localizedTitle(language: AppLanguage): String =
        if (language == AppLanguage.AR) titleAr else titleEn

    fun localizedMessage(language: AppLanguage): String =
        if (language == AppLanguage.AR) messageAr else messageEn
}
