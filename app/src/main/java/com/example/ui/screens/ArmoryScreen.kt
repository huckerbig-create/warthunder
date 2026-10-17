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
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.WeaponCategory
import com.example.data.model.WeaponSpec
import com.example.ui.localization.AppStrings
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArmoryScreen(
    currentLanguage: AppLanguage,
    weapons: List<WeaponSpec>,
    onSelectWeapon: (WeaponSpec) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<WeaponCategory?>(null) }

    val filteredWeapons = remember(weapons, searchQuery, selectedCategory) {
        weapons.filter { w ->
            (selectedCategory == null || w.category == selectedCategory) &&
            (searchQuery.isBlank() ||
                w.name.contains(searchQuery, ignoreCase = true) ||
                w.arabicExplanation.contains(searchQuery, ignoreCase = true) ||
                w.seekerType.contains(searchQuery, ignoreCase = true) ||
                w.compatibleVehicles.any { it.contains(searchQuery, ignoreCase = true) }
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CarbonDark)
    ) {
        // Search Input
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .testTag("armory_search_input"),
            placeholder = {
                Text(
                    text = if (currentLanguage == AppLanguage.AR) "ابحث عن صاروخ (AIM-120, R-77, MICA, R-73)..." else "Search missiles (AIM-120, R-77, MICA, R-73)...",
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

        // Weapon Category Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            FilterChip(
                selected = selectedCategory == null,
                onClick = { selectedCategory = null },
                label = { Text(text = AppStrings.all(currentLanguage), fontSize = 12.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = TacticalAmber,
                    selectedLabelColor = CarbonDark,
                    containerColor = CarbonCard,
                    labelColor = TextSecondary
                )
            )

            WeaponCategory.values().forEach { cat ->
                FilterChip(
                    selected = selectedCategory == cat,
                    onClick = {
                        selectedCategory = if (selectedCategory == cat) null else cat
                    },
                    label = {
                        Text(
                            text = if (currentLanguage == AppLanguage.AR) cat.nameAr else cat.nameEn,
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

        // Weapons List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp)
        ) {
            items(filteredWeapons, key = { it.id }) { weapon ->
                WeaponCard(
                    weapon = weapon,
                    language = currentLanguage,
                    onOpenSource = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(weapon.sourceUrl))
                            context.startActivity(intent)
                        } catch (_: Exception) {}
                    }
                )
            }
        }
    }
}

@Composable
fun WeaponCard(
    weapon: WeaponSpec,
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
        // Title Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = weapon.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(TacticalAmber.copy(alpha = 0.15f))
                            .border(0.6.dp, TacticalAmber.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = weapon.statusBadge,
                            color = TacticalAmber,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = weapon.localizedExplanation(language),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }

        // Telemetry Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            TelemetryTile(
                label = AppStrings.maxG(language),
                value = "${weapon.maxOverloadG}G",
                previousValue = weapon.previousOverloadG?.let { "${it}G" },
                accentColor = if (weapon.newOverloadG != null && weapon.previousOverloadG != null && weapon.newOverloadG > weapon.previousOverloadG) RadarGreen else TacticalAmber,
                modifier = Modifier.weight(1f)
            )
            TelemetryTile(
                label = AppStrings.speed(language),
                value = "Mach ${weapon.maxSpeedMach}",
                previousValue = weapon.previousSpeedMach?.let { "Mach $it" },
                accentColor = TacticalCyan,
                modifier = Modifier.weight(1f)
            )
            TelemetryTile(
                label = AppStrings.range(language),
                value = "${weapon.maxRangeKm} km",
                accentColor = RadarGreen,
                modifier = Modifier.weight(1f)
            )
        }

        // Seeker & ECCM Breakdown
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(CarbonCardElevated)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ElectricBolt,
                    contentDescription = null,
                    tint = TacticalAmber,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "${AppStrings.missileGuidance(language)}: ${weapon.localizedSeeker(language)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextPrimary,
                    fontWeight = FontWeight.Medium
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = TacticalCyan,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "${AppStrings.countermeasureResistance(language)}: ${weapon.localizedEccm(language)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }

        // Compatible Vehicles Row
        Text(
            text = "${AppStrings.compatibleAircraft(language)}: ${weapon.compatibleVehicles.joinToString(", ")}",
            style = MaterialTheme.typography.bodySmall,
            color = TextMuted,
            fontSize = 11.sp
        )

        // Documented Modifications
        val mods = weapon.localizedModifications(language)
        if (mods.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                    text = AppStrings.documentedTweaks(language),
                    color = TacticalAmber,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
                mods.forEach { mod ->
                    Text(
                        text = "• $mod",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }
            }
        }

        // Official Changelog Link
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

@Composable
fun TelemetryTile(
    label: String,
    value: String,
    previousValue: String? = null,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(CarbonCardElevated)
            .border(0.6.dp, TacticalBorder, RoundedCornerShape(6.dp))
            .padding(8.dp)
    ) {
        Text(text = label, color = TextMuted, fontSize = 9.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, color = accentColor, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        if (previousValue != null) {
            Text(
                text = "Was: $previousValue",
                color = DangerRed,
                fontSize = 9.sp
            )
        }
    }
}
