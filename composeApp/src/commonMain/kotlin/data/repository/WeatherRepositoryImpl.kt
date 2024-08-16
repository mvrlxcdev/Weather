package data.repository

import data.remote.models.WeatherDTO
import data.remote.utils.apiCall
import domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.encodedPath
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import utils.Constants.BASE_URL
import utils.ResultState

class WeatherRepositoryImpl(
    private val httpClient: HttpClient
) : WeatherRepository {
    override suspend fun fetchCurrentWeather(country: String): Flow<ResultState<WeatherDTO>> {
        return flowOf(
            apiCall {
                httpClient.get(urlString = "/v1/current.json") {
                    parameter(
                        "q", country,
                    )
                }.body<WeatherDTO>()
            }
        )
    }
}