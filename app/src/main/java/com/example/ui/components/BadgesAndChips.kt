package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.ChangeType
import com.example.data.model.ConfidenceLevel
import com.example.data.model.Nation
import com.example.ui.theme.*

@Composable
fun ConfidenceBadge(
    confidence: ConfidenceLevel,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val (bgColor, textColor, icon) = when (confidence) {
        ConfidenceLevel.OFFICIAL_CONFIRMED -> Triple(
            RadarGreen.copy(alpha = 0.15f),
            RadarGreen,
            Icons.Default.CheckCircle
        )
        ConfidenceLevel.DEV_SERVER -> Triple(
            TacticalCyan.copy(alpha = 0.15f),
            TacticalCyan,
            Icons.Default.Info
        )
        ConfidenceLevel.DATAMINE -> Triple(
            TacticalAmber.copy(alpha = 0.15f),
            TacticalAmber,
            Icons.Default.Warning
        )
        ConfidenceLevel.UNVERIFIED_COMMUNITY -> Triple(
            DangerRed.copy(alpha = 0.15f),
            DangerRed,
            Icons.Default.Warning
        )
    }

    val label = if (language == AppLanguage.AR) confidence.labelAr else confidence.labelEn

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bgColor)
            .border(0.8.dp, textColor.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.size(12.dp)
        )
        Text(
            text = label,
            color = textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ChangeTypeChip(
    type: ChangeType,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val (bgColor, textColor) = when (type) {
        ChangeType.BUFF -> Pair(RadarGreen.copy(alpha = 0.18f), RadarGreen)
        ChangeType.NERF -> Pair(DangerRed.copy(alpha = 0.18f), DangerRed)
        ChangeType.BUGFIX -> Pair(TacticalCyan.copy(alpha = 0.18f), TacticalCyan)
        ChangeType.MECHANICS -> Pair(TacticalAmber.copy(alpha = 0.18f), TacticalAmber)
        ChangeType.BR_CHANGE -> Pair(TechPurple.copy(alpha = 0.18f), TechPurple)
    }

    val label = if (language == AppLanguage.AR) type.labelAr else type.labelEn

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bgColor)
            .border(0.8.dp, textColor.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NationBadge(
    nation: Nation,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(CarbonCardElevated)
            .border(0.6.dp, TacticalBorder, RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = nation.flagEmoji, fontSize = 12.sp)
        Text(
            text = nation.localizedName(language),
            color = TextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun BRBadge(
    br: Double,
    modeLabel: String = "RB",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(TacticalAmber.copy(alpha = 0.15f))
            .border(0.8.dp, TacticalAmber.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Text(
            text = modeLabel,
            color = TacticalAmber,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "%.1f".format(br),
            color = TextPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
