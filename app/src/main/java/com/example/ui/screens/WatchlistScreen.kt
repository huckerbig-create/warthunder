package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.NotificationItem
import com.example.data.model.Vehicle
import com.example.ui.components.ChangeTypeChip
import com.example.ui.localization.AppStrings
import com.example.ui.theme.*

@Composable
fun WatchlistScreen(
    currentLanguage: AppLanguage,
    followedVehicles: List<Vehicle>,
    favoriteVehicles: List<Vehicle>,
    notifications: List<NotificationItem>,
    onSelectVehicle: (Vehicle) -> Unit,
    onToggleFollow: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onMarkAllRead: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CarbonDark)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // Followed Vehicles Section
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = TacticalAmber,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.AR) "المركبات قيد المتابعة النشطة" else "Followed Vehicles",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                if (followedVehicles.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(CarbonCard)
                            .border(0.8.dp, TacticalBorder, RoundedCornerShape(8.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = AppStrings.followHint(currentLanguage),
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                    }
                } else {
                    followedVehicles.forEach { vehicle ->
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

        // Favorites Section
        if (favoriteVehicles.isNotEmpty()) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = DangerRed,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = if (currentLanguage == AppLanguage.AR) "المركبات المفضلة" else "Favorite Vehicles",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }

                    favoriteVehicles.filterNot { followedVehicles.any { f -> f.id == it.id } }.forEach { vehicle ->
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

        // Notifications Feed Header
        item {
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
                        imageVector = Icons.Default.NotificationsActive,
                        contentDescription = null,
                        tint = TacticalCyan,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.AR) "سجل التنبيهات والإشعارات" else "Intelligence Alerts History",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                TextButton(
                    onClick = onMarkAllRead,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DoneAll,
                            contentDescription = null,
                            tint = TacticalCyan,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = if (currentLanguage == AppLanguage.AR) "تحديد كمقروء" else "Mark Read",
                            color = TacticalCyan,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        // Notification Items
        items(notifications, key = { it.id }) { notif ->
            NotificationCard(item = notif, language = currentLanguage)
        }
    }
}

@Composable
fun NotificationCard(
    item: NotificationItem,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (item.isRead) CarbonCard else CarbonCardElevated)
            .border(
                width = if (item.isRead) 0.6.dp else 1.dp,
                color = if (item.isRead) TacticalBorder else TacticalCyan.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChangeTypeChip(type = item.type, language = language)
                Text(text = item.timestamp, color = TextMuted, fontSize = 10.sp)
            }

            Text(
                text = item.localizedTitle(language),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Text(
                text = item.localizedMessage(language),
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}
