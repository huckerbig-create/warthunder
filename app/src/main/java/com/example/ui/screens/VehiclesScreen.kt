package com.example.ui.screens

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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.*
import com.example.ui.VehicleFilterState
import com.example.ui.components.BRBadge
import com.example.ui.components.ChangeTypeChip
import com.example.ui.components.NationBadge
import com.example.ui.localization.AppStrings
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiclesScreen(
    currentLanguage: AppLanguage,
    category: VehicleCategory, // AIRCRAFT or GROUND
    vehicles: List<Vehicle>,
    filterState: VehicleFilterState,
    onUpdateQuery: (String) -> Unit,
    onSetNation: (Nation?) -> Unit,
    onSetRank: (Int?) -> Unit,
    onSetTopTierOnly: (Boolean) -> Unit,
    onResetFilters: () -> Unit,
    onToggleFollow: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onSelectVehicle: (Vehicle) -> Unit,
    modifier: Modifier = Modifier
) {
    // Filter vehicles by category, query, nation, rank, top tier
    val filteredVehicles = remember(vehicles, category, filterState) {
        vehicles.filter { v ->
            v.category == category &&
            (filterState.selectedNation == null || v.nation == filterState.selectedNation) &&
            (filterState.selectedRank == null || v.rank == filterState.selectedRank) &&
            (!filterState.onlyTopTier || v.isTopTier) &&
            (filterState.query.isBlank() ||
                v.name.contains(filterState.query, ignoreCase = true) ||
                v.roleArExplanation.contains(filterState.query, ignoreCase = true) ||
                v.nation.nameEn.contains(filterState.query, ignoreCase = true) ||
                v.nation.nameAr.contains(filterState.query, ignoreCase = true) ||
                v.radarAndSensors.contains(filterState.query, ignoreCase = true) ||
                v.weaponsAndAmmunition.any { it.contains(filterState.query, ignoreCase = true) }
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CarbonDark)
    ) {
        // Search TextField
        OutlinedTextField(
            value = filterState.query,
            onValueChange = onUpdateQuery,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .testTag("vehicle_search_input"),
            placeholder = {
                Text(
                    text = if (category == VehicleCategory.AIRCRAFT) {
                        if (currentLanguage == AppLanguage.AR) "ابحث عن مقاتلة (F-16C, Gripen, Su-27)..." else "Search jets (F-16C, Gripen, Su-27)..."
                    } else {
                        if (currentLanguage == AppLanguage.AR) "ابحث عن دبابة (T-90M, Leopard 2A7V, Abrams)..." else "Search tanks (T-90M, Leopard 2A7V, Abrams)..."
                    },
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
            trailingIcon = {
                if (filterState.query.isNotEmpty()) {
                    IconButton(onClick = { onUpdateQuery("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = TextSecondary
                        )
                    }
                }
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

        // Filter Chips Row (Nations & Ranks)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // "All" filter chip
            FilterChip(
                selected = filterState.selectedNation == null && filterState.selectedRank == null && !filterState.onlyTopTier,
                onClick = onResetFilters,
                label = { Text(text = AppStrings.all(currentLanguage), fontSize = 12.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = TacticalAmber,
                    selectedLabelColor = CarbonDark,
                    containerColor = CarbonCard,
                    labelColor = TextSecondary
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = filterState.selectedNation == null && filterState.selectedRank == null && !filterState.onlyTopTier,
                    borderColor = TacticalBorder,
                    selectedBorderColor = TacticalAmber
                )
            )

            // Top-Tier Only Toggle Chip
            FilterChip(
                selected = filterState.onlyTopTier,
                onClick = { onSetTopTierOnly(!filterState.onlyTopTier) },
                label = {
                    Text(
                        text = if (currentLanguage == AppLanguage.AR) "الرتب العليا (Top-Tier)" else "Top-Tier Only",
                        fontSize = 12.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = TacticalCyan,
                    selectedLabelColor = CarbonDark,
                    containerColor = CarbonCard,
                    labelColor = TextSecondary
                )
            )

            // Nation Filter Chips
            Nation.values().forEach { nation ->
                FilterChip(
                    selected = filterState.selectedNation == nation,
                    onClick = {
                        onSetNation(if (filterState.selectedNation == nation) null else nation)
                    },
                    label = {
                        Text(
                            text = "${nation.flagEmoji} ${nation.localizedName(currentLanguage)}",
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

        // Vehicles List or Empty State
        if (filteredVehicles.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SearchOff,
                        contentDescription = null,
                        tint = TextMuted,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = AppStrings.noResults(currentLanguage),
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Button(
                        onClick = onResetFilters,
                        colors = ButtonDefaults.buttonColors(containerColor = CarbonCardElevated)
                    ) {
                        Text(
                            text = if (currentLanguage == AppLanguage.AR) "إعادة ضبط المرشحات" else "Reset Filters",
                            color = TacticalCyan
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp)
            ) {
                items(filteredVehicles, key = { it.id }) { vehicle ->
                    VehicleCard(
                        vehicle = vehicle,
                        language = currentLanguage,
                        onSelect = { onSelectVehicle(vehicle) },
                        onToggleFollow = { onToggleFollow(vehicle.id) },
                        onToggleFavorite = { onToggleFavorite(vehicle.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun VehicleCard(
    vehicle: Vehicle,
    language: AppLanguage,
    onSelect: () -> Unit,
    onToggleFollow: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(CarbonCard)
            .border(
                width = if (vehicle.isFollowed) 1.2.dp else 0.8.dp,
                color = if (vehicle.isFollowed) TacticalAmber else TacticalBorder,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onSelect() }
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header Row: Name + Nation + Rank + BR
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
                        text = vehicle.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    if (vehicle.isTopTier) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(3.dp))
                                .background(TacticalAmber.copy(alpha = 0.2f))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "TOP",
                                color = TacticalAmber,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = vehicle.localizedRole(language),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    maxLines = 1
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                NationBadge(nation = vehicle.nation, language = language)
                BRBadge(br = vehicle.brRealistic, modeLabel = "RB")
            }
        }

        // Telemetry Spec Pills
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            SpecPill(
                label = if (language == AppLanguage.AR) "السرعة" else "Speed",
                value = vehicle.maxSpeed.take(16),
                modifier = Modifier.weight(1f)
            )
            SpecPill(
                label = if (language == AppLanguage.AR) "الرتبة" else "Rank",
                value = "Rank ${vehicle.rank}",
                modifier = Modifier.weight(0.7f)
            )
            SpecPill(
                label = if (language == AppLanguage.AR) "أركيد/محاكي" else "AB / SB",
                value = "%.1f / %.1f".format(vehicle.brArcade, vehicle.brSimulator),
                modifier = Modifier.weight(1f)
            )
        }

        // Recent Changes Preview
        if (vehicle.recentChanges.isNotEmpty()) {
            val change = vehicle.recentChanges.first()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(CarbonCardElevated)
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ChangeTypeChip(type = change.type, language = language)
                Text(
                    text = if (language == AppLanguage.AR) change.summaryAr else change.summaryEn,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextPrimary,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Action Buttons Row: Follow, Favorite, Inspect
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Follow Button
                Button(
                    onClick = onToggleFollow,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (vehicle.isFollowed) TacticalAmber else CarbonCardElevated,
                        contentColor = if (vehicle.isFollowed) CarbonDark else TextSecondary
                    ),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Icon(
                        imageVector = if (vehicle.isFollowed) Icons.Default.NotificationsActive else Icons.Default.NotificationsNone,
                        contentDescription = "Follow",
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (vehicle.isFollowed) {
                            if (language == AppLanguage.AR) "متابع" else "Following"
                        } else {
                            if (language == AppLanguage.AR) "متابعة" else "Follow"
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Favorite Button
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (vehicle.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (vehicle.isFavorite) DangerRed else TextMuted,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Inspect arrow
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.clickable { onSelect() }
            ) {
                Text(
                    text = if (language == AppLanguage.AR) "التفاصيل" else "Details",
                    color = TacticalCyan,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = TacticalCyan,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun SpecPill(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(CarbonCardElevated)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Text(text = label, color = TextMuted, fontSize = 9.sp)
        Text(
            text = value,
            color = TextPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
    }
}
