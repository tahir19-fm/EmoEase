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

val LightThemeColors = lightColors(
    primary = Color(0xFF6200EE),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC6),
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)


val LightThemeColors1 = lightColors(
    primary = Color(0xFF03224E),
    secondary = Color(0xFF7CB342),
    background = Color(0xFFECEFF1),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)


val LightThemeColors2 = lightColors(
    primary = Color(0xFF03224E),
    primaryVariant = Color(0xFF001529),
    secondary = Color(0xFF03DAC6),
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)
private val BlackAndWhiteColors = lightColors(
    primary = Color.White,
    onPrimary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Color.White,
    onSecondary = Color.Black,
    secondaryVariant = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color.Red,
    onError = Color.White
)

private val LightThemeColors5 = lightColors(
    primary = Color(0xFF99F368),
    onPrimary = Color.Black,
    secondary = Color(0xFF99F368),
    onSecondary = Color.Black,
    background = Color(0xFF3D4E33),
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color.Red,
    onError = Color.White
    // Customize other color attributes as needed
)
@Composable
fun EmoEaseTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightThemeColors5
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}