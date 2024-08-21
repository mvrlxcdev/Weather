package features.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    surface = DarkTertiary,
    onSurface = Color.Black,
    background = Color.Black

)

private val LightColorPalette = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    surface = LightTertiary,
    onSurface = Color.Black,
    background = Color.Black,
)

@Composable
fun WeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = Shapes
    ) {
        content()
    }
}