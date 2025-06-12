package com.aramin.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = DeepBlue,
    secondary = Lavender,
    background = DarkGrey,
    onBackground = Color.White
)

private val LightColors = lightColorScheme(
    primary = DeepBlue,
    secondary = Lavender,
    background = Color.White,
    onBackground = DarkGrey
)

@Composable
fun AraminTheme(content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme()
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = MaterialTheme.typography,
        content = content
    )
}
