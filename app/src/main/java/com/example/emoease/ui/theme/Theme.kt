package com.example.emoease.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF4CAF50),
    secondary = Color(0xFF795548),
    background = Color(0xFF212121),
    surface = Color(0xFF000000),
    onPrimary = Color(0xFFD7CCC8),
    onSecondary = Color(0xFFD7CCC8),
    onBackground = Color(0xFFF5F5F5),
    onSurface = Color(0xFFF5F5F5)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFC1F880),
    secondary = Color(0xFFDCF8BC),
    background = Color(0xFFF5F5F5),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF3E2723),
    onSecondary = Color.White,
    onBackground = Color(0xFF616161),
    onSurface = Color(0xFF616161)
)

@Composable
fun EmoEaseTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}