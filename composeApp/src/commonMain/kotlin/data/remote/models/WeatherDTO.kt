package data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    val current: Current,
    val location: Location
)