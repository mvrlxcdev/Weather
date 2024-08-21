package features.screen.weather

import androidx.lifecycle.viewModelScope
import data.model.ForecastInfo
import domain.repository.WeatherRepository
import domain.viewstate.ViewEvent
import domain.viewstate.weather.WeatherViewState
import features.base.BaseViewModel
import kotlinx.coroutines.launch
import utils.ResultState
import utils.getDayOfWeek
import utils.weatherIcon

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : BaseViewModel<WeatherViewState, WeatherViewEvent>() {

    init {
        getWeatherByCountry("Moscow")
    }

    override fun createInitialState(): WeatherViewState = WeatherViewState()

    override fun onTriggerEvent(event: WeatherViewEvent) {
        viewModelScope.launch {
            when (event) {
                is WeatherViewEvent.ChangeCountry -> {}
            }
        }
    }

    private fun getWeatherByCountry(country: String) {
        viewModelScope.launch {
            weatherRepository.fetchWeatherForecast(country = country, days = 7).collect {
                when (it) {
                    is ResultState.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }

                    is ResultState.Failure -> {
                        setState {
                            currentState.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = it.exception.toString()
                            )
                        }
                    }

                    is ResultState.Success -> {
                        setState {
                            currentState.copy(
                                isLoading = false,
                                temperature = it.data.current.temp_c.toInt().toString(),
                                icon = weatherIcon(it.data.current.condition.code),
                                country = it.data.location.region,
                                condition = it.data.current.condition.text,
                                windKph = it.data.current.wind_kph.toInt().toString(),
                                humidity = it.data.current.humidity.toString(),
                                //airQuality = it.data.location.,
                                UVIndex = it.data.current.uv.toString(),
                                //sunrise = it.data.current.s,
                                wind = it.data.current.wind_dir,
                                // rainFall = it.data.current.ra,
                                fellsLike = it.data.current.feelslike_c.toString(),
                                visibility = it.data.current.vis_km.toString(),

                                forecastList = it.data.forecast.forecastday.map {
                                    ForecastInfo(
                                        icon = weatherIcon(it.day.condition.code),
                                        temp = it.day.avgtemp_c.toInt().toString(),
                                        day = getDayOfWeek(it.date)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}

sealed class WeatherViewEvent : ViewEvent {
    class ChangeCountry(val country: String) : WeatherViewEvent()
}