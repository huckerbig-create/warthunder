package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.repository.WarThunderRepository
import com.example.ui.localization.AppStrings
import com.example.ui.theme.*

@Composable
fun SettingsScreen(
    currentLanguage: AppLanguage,
    repository: WarThunderRepository,
    lastSyncTime: String,
    syncMessage: String?,
    isSyncing: Boolean,
    onSetLanguage: (AppLanguage) -> Unit,
    onSyncNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var notifyMajor by remember { mutableStateOf(repository.notifyMajorUpdates) }
    var notifyBr by remember { mutableStateOf(repository.notifyBrChanges) }
    var notifyMissiles by remember { mutableStateOf(repository.notifyMissileTweaks) }
    var notifyFollowed by remember { mutableStateOf(repository.notifyFollowedVehicles) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CarbonDark)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // Language Section
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = TacticalCyan,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = AppStrings.settingsLanguageTitle(currentLanguage),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
                Text(
                    text = AppStrings.settingsLanguageDesc(currentLanguage),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )

                // Language Option Cards
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // English
                    LanguageSelectOption(
                        title = "English (LTR)",
                        subtitle = "Left-to-Right Interface",
                        isSelected = currentLanguage == AppLanguage.EN,
                        onClick = { onSetLanguage(AppLanguage.EN) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("select_english_button")
                    )

                    // Arabic
                    LanguageSelectOption(
                        title = "العربية (RTL)",
                        subtitle = "واجهة كاملة من اليمين لليسار",
                        isSelected = currentLanguage == AppLanguage.AR,
                        onClick = { onSetLanguage(AppLanguage.AR) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("select_arabic_button")
                    )
                }
            }
        }

        // Live Synchronization Status & Controls
        item {
            Column(
                modifier = Modifier
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
                    Column {
                        Text(
                            text = if (currentLanguage == AppLanguage.AR) "حالة المزامنة والبيانات" else "Data Synchronization",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = AppStrings.lastSync(currentLanguage, lastSyncTime),
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                        if (syncMessage != null) {
                            Text(
                                text = syncMessage,
                                color = TacticalAmber,
                                fontSize = 11.sp
                            )
                        }
                    }

                    Button(
                        onClick = onSyncNow,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TacticalAmber,
                            contentColor = CarbonDark
                        ),
                        shape = RoundedCornerShape(6.dp),
                        enabled = !isSyncing
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isSyncing) AppStrings.syncing(currentLanguage) else AppStrings.syncNow(currentLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        // Configurable Notifications
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(CarbonCard)
                    .border(0.8.dp, TacticalBorder, RoundedCornerShape(10.dp))
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = TacticalAmber,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = AppStrings.settingsNotifTitle(currentLanguage),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                SettingSwitchRow(
                    title = AppStrings.notifyMajorUpdates(currentLanguage),
                    checked = notifyMajor,
                    onCheckedChange = {
                        notifyMajor = it
                        repository.notifyMajorUpdates = it
                    }
                )

                SettingSwitchRow(
                    title = AppStrings.notifyBrChanges(currentLanguage),
                    checked = notifyBr,
                    onCheckedChange = {
                        notifyBr = it
                        repository.notifyBrChanges = it
                    }
                )

                SettingSwitchRow(
                    title = AppStrings.notifyMissileTweaks(currentLanguage),
                    checked = notifyMissiles,
                    onCheckedChange = {
                        notifyMissiles = it
                        repository.notifyMissileTweaks = it
                    }
                )

                SettingSwitchRow(
                    title = AppStrings.notifyFollowedVehicles(currentLanguage),
                    checked = notifyFollowed,
                    onCheckedChange = {
                        notifyFollowed = it
                        repository.notifyFollowedVehicles = it
                    }
                )
            }
        }

        // Official Sources Links
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(CarbonCard)
                    .border(0.8.dp, TacticalBorder, RoundedCornerShape(10.dp))
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = RadarGreen,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = AppStrings.officialSourcesTitle(currentLanguage),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                OfficialSourceLinkItem(
                    title = "War Thunder Official News",
                    url = "https://warthunder.com/en/news/",
                    onClick = {
                        try {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://warthunder.com/en/news/")))
                        } catch (_: Exception) {}
                    }
                )

                OfficialSourceLinkItem(
                    title = "War Thunder Official Changelog",
                    url = "https://warthunder.com/en/game/changelog/",
                    onClick = {
                        try {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://warthunder.com/en/game/changelog/")))
                        } catch (_: Exception) {}
                    }
                )

                OfficialSourceLinkItem(
                    title = "War Thunder Official Wiki",
                    url = "https://wiki.warthunder.com/",
                    onClick = {
                        try {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wiki.warthunder.com/")))
                        } catch (_: Exception) {}
                    }
                )
            }
        }

        // Translation & Intellectual Property Disclaimer
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(CarbonCardElevated)
                    .border(0.6.dp, TacticalBorder, RoundedCornerShape(10.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.AR) "بيان التوثيق والترجمة" else "Documentation & Translation Notice",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                }
                Text(
                    text = AppStrings.disclaimerText(currentLanguage),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun LanguageSelectOption(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) TacticalAmber.copy(alpha = 0.15f) else CarbonCard)
            .border(
                width = if (isSelected) 1.5.dp else 0.8.dp,
                color = if (isSelected) TacticalAmber else TacticalBorder,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) TacticalAmber else TextPrimary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            fontSize = 10.sp
        )
    }
}

@Composable
fun SettingSwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = CarbonDark,
                checkedTrackColor = TacticalAmber,
                uncheckedThumbColor = TextMuted,
                uncheckedTrackColor = CarbonCardElevated
            )
        )
    }
}

@Composable
fun OfficialSourceLinkItem(
    title: String,
    url: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(CarbonCardElevated)
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Text(
                text = url,
                style = MaterialTheme.typography.bodySmall,
                color = TacticalCyan,
                fontSize = 11.sp
            )
        }
        Icon(
            imageVector = Icons.Default.OpenInNew,
            contentDescription = null,
            tint = TacticalCyan,
            modifier = Modifier.size(16.dp)
        )
    }
}
