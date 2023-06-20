package com.example.emoease.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFFB09E99),
    onPrimary = Color.White,
    secondary = Color(0xFFFEE9E1),
    onSecondary = Color.White,
    background = Color(0xFFFAD4C0),
    onBackground = Color.White,
    surface = Color(0xFF64B6AC),
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFB09E99),
    onPrimary = Color.Black,
    secondary = Color(0xFFFEE9E1),
    onSecondary = Color.Black,
    background = Color(0xFFFAD4C0),
    onBackground = Color.Black,
    surface = Color(0xFF64B6AC),
    onSurface = Color.Black
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