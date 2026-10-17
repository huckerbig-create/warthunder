package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.localization.AppStrings
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithLanguage(
    currentLanguage: AppLanguage,
    isSyncing: Boolean,
    unreadNotificationsCount: Int,
    onToggleLanguage: () -> Unit,
    onSyncClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "sync_rotate")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    TopAppBar(
        modifier = modifier
            .background(CarbonDark)
            .border(
                width = 0.5.dp,
                color = TacticalBorder,
                shape = RoundedCornerShape(0.dp)
            ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = CarbonDark,
            titleContentColor = TextPrimary
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Radar status dot
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(TacticalCyan, RadarGreen)
                            )
                        )
                )

                Column {
                    Text(
                        text = AppStrings.appTitle(currentLanguage),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.AR) "وور ثاندر • تحديثات وتوازن" else "War Thunder Live Intel",
                        style = MaterialTheme.typography.labelSmall,
                        color = TacticalAmber
                    )
                }
            }
        },
        actions = {
            // Language Switcher Badge Button
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(CarbonCardElevated)
                    .border(1.dp, TacticalCyan.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                    .clickable { onToggleLanguage() }
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .testTag("language_toggle_button"),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Translate,
                    contentDescription = "Language",
                    tint = TacticalCyan,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = if (currentLanguage == AppLanguage.AR) "العربية" else "English",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = if (currentLanguage == AppLanguage.AR) "[EN]" else "[ع]",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = TacticalAmber
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            // Sync Button
            IconButton(
                onClick = onSyncClick,
                modifier = Modifier
                    .size(40.dp)
                    .testTag("sync_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Sync",
                    tint = if (isSyncing) TacticalAmber else TextSecondary,
                    modifier = if (isSyncing) Modifier.rotate(rotation) else Modifier
                )
            }

            // Notifications Badge Button
            IconButton(
                onClick = onNotificationsClick,
                modifier = Modifier
                    .size(40.dp)
                    .testTag("notifications_button")
            ) {
                Box {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = if (unreadNotificationsCount > 0) TacticalAmber else TextSecondary
                    )
                    if (unreadNotificationsCount > 0) {
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .align(Alignment.TopEnd)
                                .clip(CircleShape)
                                .background(DangerRed),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = unreadNotificationsCount.toString(),
                                fontSize = 9.sp,
                                color = TextPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    )
}
