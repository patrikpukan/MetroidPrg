package tech.pukan.metroidprg.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = MetroRed,
    onPrimary = Color.White,
    secondary = TramBlue,
    onSecondary = Color.White,
    background = NeutralLight,
    onBackground = NeutralDark,
    surface = Color.White,
    onSurface = NeutralDark
)

private val DarkColors = darkColorScheme(
    primary = MetroRed,
    onPrimary = Color.White,
    secondary = TramBlue,
    onSecondary = Color.White,
    background = NeutralDark,
    onBackground = NeutralLight,
    surface = NeutralDark,
    onSurface = NeutralLight
)

@Composable
fun MetroidPrgTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

fun MaterialTheme(
    colorScheme: ColorScheme,
    typography: androidx.compose.material3.Typography,
    shapes: Shapes,
    content: () -> Unit
) {
    TODO("Not yet implemented")
}
