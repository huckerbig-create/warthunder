package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val TacticalDarkColorScheme = darkColorScheme(
    primary = TacticalAmber,
    onPrimary = CarbonDark,
    primaryContainer = TacticalAmberGlow,
    onPrimaryContainer = TacticalAmber,
    secondary = TacticalCyan,
    onSecondary = CarbonDark,
    secondaryContainer = TacticalCyanGlow,
    onSecondaryContainer = TacticalCyan,
    tertiary = RadarGreen,
    onTertiary = CarbonDark,
    background = CarbonDark,
    onBackground = TextPrimary,
    surface = CarbonSurface,
    onSurface = TextPrimary,
    surfaceVariant = CarbonCard,
    onSurfaceVariant = TextSecondary,
    outline = TacticalBorder,
    outlineVariant = CarbonCardElevated,
    error = DangerRed,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Default to sleek tactical military dark theme
    dynamicColor: Boolean = false, // Force consistent tactical aesthetic
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TacticalDarkColorScheme,
        typography = AppTypography,
        content = content
    )
}
