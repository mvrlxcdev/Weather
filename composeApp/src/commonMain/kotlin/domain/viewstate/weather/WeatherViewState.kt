package domain.viewstate.weather

import data.model.ForecastInfo
import domain.viewstate.ViewState
import org.jetbrains.compose.resources.DrawableResource
import weather.composeapp.generated.resources.Res
import weather.composeapp.generated.resources.wind_black

data class WeatherViewState(
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = true,
    val icon: DrawableResource = Res.drawable.wind_black,
    val country: String = "",
    val temperature: String = "",
    val condition: String = "",
    val windKph: String = "",
    val humidity: String = "",
    val airQuality: String = "",
    val UVIndex: String = "",
    val sunrise: String = "",
    val wind: String = "",
    val rainFall: String = "",
    val fellsLike: String = "",
    val visibility: String = "",

    val forecastList: List<ForecastInfo> = emptyList(),
    val code: Int = 0
) : ViewState