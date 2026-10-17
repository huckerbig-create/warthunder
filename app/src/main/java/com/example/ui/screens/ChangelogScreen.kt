package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.*
import com.example.ui.components.BRBadge
import com.example.ui.components.ChangeTypeChip
import com.example.ui.components.ConfidenceBadge
import com.example.ui.components.NationBadge
import com.example.ui.localization.AppStrings
import com.example.ui.theme.*

enum class ChangelogTab {
    CHANGES,
    BR_SHIFTS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangelogScreen(
    currentLanguage: AppLanguage,
    changelogEntries: List<ChangelogEntry>,
    brShifts: List<BRShift>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var activeSubTab by remember { mutableStateOf(ChangelogTab.CHANGES) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedChangeType by remember { mutableStateOf<ChangeType?>(null) }
    var selectedNation by remember { mutableStateOf<Nation?>(null) }

    val filteredChangelog = remember(changelogEntries, searchQuery, selectedChangeType, selectedNation) {
        changelogEntries.filter { entry ->
            (selectedChangeType == null || entry.changeType == selectedChangeType) &&
            (selectedNation == null || entry.nation == selectedNation) &&
            (searchQuery.isBlank() ||
                entry.vehicleOrWeapon.contains(searchQuery, ignoreCase = true) ||
                entry.detailsEn.contains(searchQuery, ignoreCase = true) ||
                entry.detailsAr.contains(searchQuery, ignoreCase = true) ||
                entry.patchVersion.contains(searchQuery, ignoreCase = true)
            )
        }
    }

    val filteredBrShifts = remember(brShifts, searchQuery, selectedNation) {
        brShifts.filter { shift ->
            (selectedNation == null || shift.nation == selectedNation) &&
            (searchQuery.isBlank() ||
                shift.vehicleName.contains(searchQuery, ignoreCase = true) ||
                shift.patchVersion.contains(searchQuery, ignoreCase = true) ||
                shift.reasonEn.contains(searchQuery, ignoreCase = true) ||
                shift.reasonAr.contains(searchQuery, ignoreCase = true)
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CarbonDark)
    ) {
        // Tab Row: [Vehicle & Weapon Changes | Battle Rating Shifts]
        TabRow(
            selectedTabIndex = activeSubTab.ordinal,
            containerColor = CarbonCard,
            contentColor = TacticalAmber,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[activeSubTab.ordinal]),
                    color = TacticalAmber
                )
            }
        ) {
            Tab(
                selected = activeSubTab == ChangelogTab.CHANGES,
                onClick = { activeSubTab = ChangelogTab.CHANGES },
                text = {
                    Text(
                        text = if (currentLanguage == AppLanguage.AR) "تعديلات المركبات والأسلحة" else "Vehicle & Weapon Changes",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            )
            Tab(
                selected = activeSubTab == ChangelogTab.BR_SHIFTS,
                onClick = { activeSubTab = ChangelogTab.BR_SHIFTS },
                text = {
                    Text(
                        text = if (currentLanguage == AppLanguage.AR) "تعديلات تصنيف المعركة (BR)" else "Battle Rating Shifts",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            )
        }

        // Search Field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .testTag("changelog_search_input"),
            placeholder = {
                Text(
                    text = if (currentLanguage == AppLanguage.AR) "ابحث في سجل التغييرات..." else "Search changes & patch notes...",
                    color = TextMuted,
                    fontSize = 13.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = TacticalAmber
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CarbonCard,
                unfocusedContainerColor = CarbonCard,
                focusedBorderColor = TacticalAmber,
                unfocusedBorderColor = TacticalBorder,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true
        )

        // Type / Filter Chips (for Changes tab)
        if (activeSubTab == ChangelogTab.CHANGES) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FilterChip(
                    selected = selectedChangeType == null && selectedNation == null,
                    onClick = {
                        selectedChangeType = null
                        selectedNation = null
                    },
                    label = { Text(text = AppStrings.all(currentLanguage), fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = TacticalAmber,
                        selectedLabelColor = CarbonDark,
                        containerColor = CarbonCard,
                        labelColor = TextSecondary
                    )
                )

                ChangeType.values().forEach { type ->
                    FilterChip(
                        selected = selectedChangeType == type,
                        onClick = {
                            selectedChangeType = if (selectedChangeType == type) null else type
                        },
                        label = {
                            Text(
                                text = if (currentLanguage == AppLanguage.AR) type.labelAr else type.labelEn,
                                fontSize = 12.sp
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TacticalCyan,
                            selectedLabelColor = CarbonDark,
                            containerColor = CarbonCard,
                            labelColor = TextSecondary
                        )
                    )
                }
            }
        }

        // List Content
        if (activeSubTab == ChangelogTab.CHANGES) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp)
            ) {
                items(filteredChangelog, key = { it.id }) { entry ->
                    ChangelogItemCard(
                        entry = entry,
                        language = currentLanguage,
                        onOpenSource = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(entry.sourceUrl))
                                context.startActivity(intent)
                            } catch (_: Exception) {}
                        }
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp)
            ) {
                items(filteredBrShifts, key = { it.id }) { shift ->
                    BRShiftCard(shift = shift, language = currentLanguage)
                }
            }
        }
    }
}

@Composable
fun ChangelogItemCard(
    entry: ChangelogEntry,
    language: AppLanguage,
    onOpenSource: () -> Unit,
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
        // Header
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
                    text = entry.vehicleOrWeapon,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                NationBadge(nation = entry.nation, language = language)
            }
            Text(
                text = "Patch ${entry.patchVersion}",
                color = TacticalAmber,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Badges: Change Type + Confidence Label
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChangeTypeChip(type = entry.changeType, language = language)
            ConfidenceBadge(confidence = entry.confidence, language = language)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = entry.date, color = TextMuted, fontSize = 11.sp)
        }

        // Previous vs New Values (if available)
        if (entry.previousValue != null && entry.newValue != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(CarbonCardElevated)
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${if (language == AppLanguage.AR) "القيمة السابقة" else "Before"}: ${entry.previousValue}",
                    color = DangerRed,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(text = "→", color = TextSecondary, fontSize = 12.sp)
                Text(
                    text = "${if (language == AppLanguage.AR) "القيمة المحدثة" else "After"}: ${entry.newValue}",
                    color = RadarGreen,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Explanation text
        Text(
            text = entry.localizedDetails(language),
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )

        // Show original English if Arabic is active
        if (language == AppLanguage.AR) {
            Text(
                text = "Original English: ${entry.detailsEn}",
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted,
                fontSize = 11.sp
            )
        }

        // Source Link
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOpenSource() },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = AppStrings.officialSource(language),
                color = TacticalCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.OpenInNew,
                contentDescription = null,
                tint = TacticalCyan,
                modifier = Modifier.size(13.dp)
            )
        }
    }
}
