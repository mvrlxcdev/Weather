package data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastDTO(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)