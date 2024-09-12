package data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class SearchLocations(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)