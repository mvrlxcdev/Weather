package domain.repository

import data.remote.models.SearchLocations
import data.remote.models.WeatherDTO
import data.remote.models.WeatherForecastDTO
import kotlinx.coroutines.flow.Flow
import utils.ResultState

interface WeatherRepository {

    suspend fun fetchCurrentWeather(country: String): Flow<ResultState<WeatherDTO>>
    suspend fun fetchWeatherForecast(country: String, days: Int): Flow<ResultState<WeatherForecastDTO>>
    suspend fun fetchLocations(param: String): Flow<ResultState<List<SearchLocations>>>

}