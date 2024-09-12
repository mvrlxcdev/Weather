package data.repository

import androidx.datastore.core.DataStore
import data.remote.models.SearchLocations
import data.remote.models.WeatherDTO
import data.remote.models.WeatherForecastDTO
import data.remote.utils.apiCall
import domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import utils.ResultState

class WeatherRepositoryImpl(
    private val httpClient: HttpClient,
) : WeatherRepository {
    override suspend fun fetchCurrentWeather(country: String): Flow<ResultState<WeatherDTO>> {
        return flowOf(
            apiCall {
                httpClient.get(urlString = "/v1/current.json") {
                    parameter("q", country)
                }.body<WeatherDTO>()
            }
        )
    }

    override suspend fun fetchWeatherForecast(
        country: String,
        days: Int
    ): Flow<ResultState<WeatherForecastDTO>> {
        return flowOf(
            apiCall {
                httpClient.get(urlString = "/v1/forecast.json") {
                    parameter("q", country)
                    parameter("days", days)
                }.body<WeatherForecastDTO>()
            }
        )
    }

    override suspend fun fetchLocations(param: String): Flow<ResultState<List<SearchLocations>>> {
        return flowOf(
            apiCall {
                httpClient.get(urlString = "/v1/search.json") {
                    parameter("q", param)
                }.body<List<SearchLocations>>()
            }
        )
    }
}