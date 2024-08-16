package domain.repository

import data.remote.models.WeatherDTO
import kotlinx.coroutines.flow.Flow
import utils.ResultState

interface WeatherRepository {

    suspend fun fetchCurrentWeather(country: String) : Flow<ResultState<WeatherDTO>>

}