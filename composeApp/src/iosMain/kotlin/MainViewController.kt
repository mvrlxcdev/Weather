import androidx.compose.ui.window.ComposeUIViewController
import data.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { WeatherApp() }