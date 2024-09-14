import androidx.compose.runtime.*
import features.screen.LocationsScreen
import features.screen.WeatherScreen
import features.ui.theme.WeatherTheme


@Composable
fun WeatherApp() {
    WeatherTheme {
       //WeatherScreen()
        LocationsScreen()
    }
}