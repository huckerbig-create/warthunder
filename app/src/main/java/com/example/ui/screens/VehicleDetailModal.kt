package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.Vehicle
import com.example.data.model.VehicleCategory
import com.example.ui.components.BRBadge
import com.example.ui.components.ChangeTypeChip
import com.example.ui.components.NationBadge
import com.example.ui.localization.AppStrings
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailModal(
    vehicle: Vehicle,
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit,
    onToggleFollow: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    val context = LocalContext.current
    var showOriginalEnglish by remember { mutableStateOf(false) }

    val activeLang = if (showOriginalEnglish) AppLanguage.EN else currentLanguage

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = CarbonSurface,
        dragHandle = { BottomSheetDefaults.DragHandle(color = TacticalBorder) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header: Name, Nation, BR, Rank
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = vehicle.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = vehicle.localizedRole(activeLang),
                        style = MaterialTheme.typography.bodyMedium,
                        color = TacticalAmber
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    NationBadge(nation = vehicle.nation, language = activeLang)
                    BRBadge(br = vehicle.brRealistic, modeLabel = "RB")
                }
            }

            // Quick Action Buttons (Follow, Favorite, Toggle Original English)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Follow Button
                Button(
                    onClick = onToggleFollow,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (vehicle.isFollowed) TacticalAmber else CarbonCardElevated,
                        contentColor = if (vehicle.isFollowed) CarbonDark else TextSecondary
                    ),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = if (vehicle.isFollowed) Icons.Default.NotificationsActive else Icons.Default.NotificationsNone,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (vehicle.isFollowed) {
                            if (activeLang == AppLanguage.AR) "متابع" else "Following"
                        } else {
                            if (activeLang == AppLanguage.AR) "متابعة التغييرات" else "Follow Changes"
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Favorite Button
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(CarbonCardElevated)
                ) {
                    Icon(
                        imageVector = if (vehicle.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (vehicle.isFavorite) DangerRed else TextMuted
                    )
                }

                // English / Arabic Toggle for this vehicle
                if (currentLanguage == AppLanguage.AR) {
                    Button(
                        onClick = { showOriginalEnglish = !showOriginalEnglish },
                        colors = ButtonDefaults.buttonColors(containerColor = CarbonCardElevated),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = if (showOriginalEnglish) "العربية" else "English Text",
                            color = TacticalCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Overview Section
            DetailSectionBox(
                title = if (activeLang == AppLanguage.AR) "نظرة استخباراتية شاملة" else "Intelligence Overview",
                icon = Icons.Default.Info
            ) {
                Text(
                    text = vehicle.localizedOverview(activeLang),
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextPrimary
                )
            }

            // Technical Specs & Telemetry Box
            DetailSectionBox(
                title = AppStrings.technicalSpecs(activeLang),
                icon = Icons.Default.Speed
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SpecDetailRow(
                        label = if (activeLang == AppLanguage.AR) "السرعة القصوى" else "Top Speed",
                        value = vehicle.maxSpeed
                    )
                    SpecDetailRow(
                        label = if (vehicle.category == VehicleCategory.AIRCRAFT) {
                            if (activeLang == AppLanguage.AR) "المحرك والدفع" else "Engine & Thrust"
                        } else {
                            if (activeLang == AppLanguage.AR) "المحرك والحركية" else "Engine & Mobility"
                        },
                        value = vehicle.engineAndMobility
                    )
                    SpecDetailRow(
                        label = if (activeLang == AppLanguage.AR) "تصنيفات المعركة (BR)" else "Battle Ratings",
                        value = "Arcade: %.1f | Realistic: %.1f | Simulator: %.1f".format(
                            vehicle.brArcade, vehicle.brRealistic, vehicle.brSimulator
                        )
                    )
                }
            }

            // Radar, Avionics & Optics Box
            DetailSectionBox(
                title = if (vehicle.category == VehicleCategory.AIRCRAFT) {
                    AppStrings.radarAndAvionics(activeLang)
                } else {
                    AppStrings.targetingAndOptics(activeLang)
                },
                icon = Icons.Default.Radar
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SpecDetailRow(
                        label = if (activeLang == AppLanguage.AR) "منظومة الرادار / إدارة النيران" else "Radar / Fire Control",
                        value = vehicle.radarAndSensors
                    )
                    SpecDetailRow(
                        label = if (activeLang == AppLanguage.AR) "المناظير والأجهزة البصرية" else "Optics & Thermals",
                        value = vehicle.opticsAndThermal
                    )
                    SpecDetailRow(
                        label = if (vehicle.category == VehicleCategory.AIRCRAFT) {
                            if (activeLang == AppLanguage.AR) "المشاعل ووسائل الدفاع" else "Countermeasures (CM)"
                        } else {
                            if (activeLang == AppLanguage.AR) "الدروع وبطانة كبح الشظايا" else "Armor & Spall Liner"
                        },
                        value = vehicle.countermeasuresAndArmor
                    )
                }
            }

            // Weapons & Ammunition Loadouts
            DetailSectionBox(
                title = AppStrings.armament(activeLang),
                icon = Icons.Default.Security
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    vehicle.weaponsAndAmmunition.forEach { weapon ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(text = "•", color = TacticalAmber, fontWeight = FontWeight.Bold)
                            Text(
                                text = weapon,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextPrimary
                            )
                        }
                    }
                }
            }

            // Balance History & Patch Timeline
            DetailSectionBox(
                title = AppStrings.balanceHistory(activeLang),
                icon = Icons.Default.History
            ) {
                if (vehicle.recentChanges.isEmpty()) {
                    Text(
                        text = if (activeLang == AppLanguage.AR) "لا توجد تعديلات توازن حديثة مسجلة لهذه المركبة." else "No recent balance adjustments recorded for this vehicle.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        vehicle.recentChanges.forEach { change ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(CarbonDark)
                                    .border(0.6.dp, TacticalBorder, RoundedCornerShape(6.dp))
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ChangeTypeChip(type = change.type, language = activeLang)
                                    Text(
                                        text = "Patch ${change.patchVersion} • ${change.date}",
                                        color = TacticalAmber,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                if (change.previousValue != null && change.newValue != null) {
                                    Text(
                                        text = "${change.previousValue} → ${change.newValue}",
                                        color = RadarGreen,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }

                                Text(
                                    text = if (activeLang == AppLanguage.AR) change.summaryAr else change.summaryEn,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextPrimary
                                )
                            }
                        }
                    }
                }
            }

            // External Links: War Thunder Wiki & Changelog
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = {
                        try {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(vehicle.wikiUrl)))
                        } catch (_: Exception) {}
                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = "War Thunder Wiki", color = TacticalCyan, fontSize = 12.sp)
                        Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, tint = TacticalCyan, modifier = Modifier.size(14.dp))
                    }
                }

                TextButton(
                    onClick = {
                        try {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(vehicle.changelogUrl)))
                        } catch (_: Exception) {}
                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = "Official Changelog", color = TacticalAmber, fontSize = 12.sp)
                        Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, tint = TacticalAmber, modifier = Modifier.size(14.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun DetailSectionBox(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CarbonCard)
            .border(0.8.dp, TacticalBorder, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TacticalCyan,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
        content()
    }
}

@Composable
fun SpecDetailRow(
    label: String,
    value: String
) {
    Column {
        Text(text = label, color = TextMuted, fontSize = 10.sp)
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = TextPrimary,
            fontWeight = FontWeight.Medium
        )
    }
}
