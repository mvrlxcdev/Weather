package data.model

import org.jetbrains.compose.resources.DrawableResource

data class ForecastInfo(
    val icon: DrawableResource,
    val temp: String,
    val day: String,
)
