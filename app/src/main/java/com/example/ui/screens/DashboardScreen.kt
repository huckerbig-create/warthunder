package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.*
import com.example.ui.MainTab
import com.example.ui.components.BRBadge
import com.example.ui.components.ChangeTypeChip
import com.example.ui.components.NationBadge
import com.example.ui.localization.AppStrings
import com.example.ui.theme.*

@Composable
fun DashboardScreen(
    currentLanguage: AppLanguage,
    news: List<NewsArticle>,
    followedVehicles: List<Vehicle>,
    brShifts: List<BRShift>,
    lastSyncTime: String,
    onNavigateTab: (MainTab) -> Unit,
    onSelectVehicle: (Vehicle) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CarbonDark)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // Hero Banner Card with Telemetry
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, TacticalBorder, RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_intel_hero),
                    contentDescription = "War Thunder Intel Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Dark gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    CarbonDark.copy(alpha = 0.85f),
                                    CarbonDark
                                )
                            )
                        )
                )

                // Overlay Text
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(TacticalAmber)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "LIVE INTEL",
                                color = CarbonDark,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = AppStrings.lastSync(currentLanguage, lastSyncTime),
                            color = TextSecondary,
                            fontSize = 11.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = AppStrings.appSubtitle(currentLanguage),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
            }
        }

        // Quick Telemetry Metrics Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MetricCard(
                    title = if (currentLanguage == AppLanguage.AR) "المقاتلات" else "Fighters",
                    value = "Rank VIII",
                    subtitle = "Fox-3 Ready",
                    accentColor = TacticalCyan,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigateTab(MainTab.AIRCRAFT) }
                )
                MetricCard(
                    title = if (currentLanguage == AppLanguage.AR) "المدرعات" else "Ground MBTs",
                    value = "BR 11.7",
                    subtitle = "Spall Liners",
                    accentColor = RadarGreen,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigateTab(MainTab.TANKS) }
                )
                MetricCard(
                    title = if (currentLanguage == AppLanguage.AR) "الصواريخ" else "Missiles",
                    value = "50G TVC",
                    subtitle = "ARH & IRCCM",
                    accentColor = TacticalAmber,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigateTab(MainTab.ARMORY) }
                )
            }
        }

        // Followed Vehicles Section
        if (followedVehicles.isNotEmpty()) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = TacticalAmber,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = AppStrings.followedAlerts(currentLanguage),
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }
                        TextButton(
                            onClick = { onNavigateTab(MainTab.WATCHLIST) },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = if (currentLanguage == AppLanguage.AR) "إدارة المراقبة" else "Manage",
                                color = TacticalCyan,
                                fontSize = 12.sp
                            )
                        }
                    }

                    followedVehicles.forEach { vehicle ->
                        FollowedVehicleCard(
                            vehicle = vehicle,
                            language = currentLanguage,
                            onClick = { onSelectVehicle(vehicle) }
                        )
                    }
                }
            }
        }

        // Recent BR Shifts Section
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = TechPurple,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = AppStrings.recentBrShifts(currentLanguage),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }
                    TextButton(
                        onClick = { onNavigateTab(MainTab.CHANGELOG) },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = if (currentLanguage == AppLanguage.AR) "عرض الكل" else "View All",
                            color = TacticalCyan,
                            fontSize = 12.sp
                        )
                    }
                }

                brShifts.take(2).forEach { brShift ->
                    BRShiftCard(shift = brShift, language = currentLanguage)
                }
            }
        }

        // Official News & Patch Notes Feed Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AppStrings.breakingNews(currentLanguage),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = AppStrings.aiTranslationBadge(currentLanguage),
                        color = TextSecondary,
                        fontSize = 10.sp
                    )
                }
            }
        }

        // News Items
        items(news, key = { it.id }) { article ->
            NewsCard(
                article = article,
                language = currentLanguage,
                onOpenSource = {
                    try {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.sourceUrl))
                        context.startActivity(browserIntent)
                    } catch (_: Exception) {}
                }
            )
        }
    }
}

@Composable
fun MetricCard(
    title: String,
    value: String,
    subtitle: String,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CarbonCard)
            .border(1.dp, TacticalBorder, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Text(text = title, color = TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, color = accentColor, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = subtitle, color = TextMuted, fontSize = 10.sp)
    }
}

@Composable
fun FollowedVehicleCard(
    vehicle: Vehicle,
    language: AppLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CarbonCard)
            .border(1.dp, TacticalAmber.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = vehicle.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                NationBadge(nation = vehicle.nation, language = language)
                BRBadge(br = vehicle.brRealistic, modeLabel = "RB")
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = vehicle.localizedRole(language),
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                maxLines = 1
            )
            if (vehicle.recentChanges.isNotEmpty()) {
                val latest = vehicle.recentChanges.first()
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    ChangeTypeChip(type = latest.type, language = language)
                    Text(
                        text = if (language == AppLanguage.AR) latest.summaryAr else latest.summaryEn,
                        color = TacticalCyan,
                        fontSize = 11.sp,
                        maxLines = 1
                    )
                }
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Details",
            tint = TacticalAmber,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun BRShiftCard(
    shift: BRShift,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CarbonCard)
            .border(0.8.dp, TacticalBorder, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = shift.vehicleName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                NationBadge(nation = shift.nation, language = language)
            }
            Text(
                text = shift.patchVersion,
                color = TextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Old vs New BR Comparison
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "RB: %.1f → %.1f".format(shift.oldRB, shift.newRB),
                color = if (shift.newRB > shift.oldRB) DangerRed else RadarGreen,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            Text(
                text = "AB: %.1f → %.1f".format(shift.oldAB, shift.newAB),
                color = TextSecondary,
                fontSize = 12.sp
            )
            Text(
                text = "SB: %.1f → %.1f".format(shift.oldSB, shift.newSB),
                color = TextSecondary,
                fontSize = 12.sp
            )
        }

        Text(
            text = shift.localizedReason(language),
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )
    }
}

@Composable
fun NewsCard(
    article: NewsArticle,
    language: AppLanguage,
    onOpenSource: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(CarbonCard)
            .border(0.8.dp, TacticalBorder, RoundedCornerShape(10.dp))
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(CarbonCardElevated)
                    .border(0.6.dp, TacticalCyan.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = if (language == AppLanguage.AR) article.category.nameAr else article.category.nameEn,
                    color = TacticalCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = article.date,
                color = TextMuted,
                fontSize = 11.sp
            )
        }

        Text(
            text = article.localizedTitle(language),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Text(
            text = article.localizedSummary(language),
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )

        // Show English original subtitle if Arabic is selected
        if (language == AppLanguage.AR) {
            Text(
                text = "Original: ${article.titleEn}",
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted,
                fontSize = 11.sp
            )
        }

        // Source Link Action
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (article.version != null) {
                Text(
                    text = "v${article.version}",
                    color = TacticalAmber,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Spacer(modifier = Modifier.width(4.dp))
            }

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onOpenSource() }
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = AppStrings.officialSource(language),
                    color = TacticalCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = Icons.Default.OpenInNew,
                    contentDescription = null,
                    tint = TacticalCyan,
                    modifier = Modifier.size(13.dp)
                )
            }
        }
    }
}
