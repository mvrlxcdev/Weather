package features.screen.weather

import androidx.lifecycle.viewModelScope
import domain.repository.WeatherRepository
import domain.viewstate.ViewEvent
import domain.viewstate.weather.WeatherViewState
import features.base.BaseViewModel
import kotlinx.coroutines.launch
import utils.ResultState

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : BaseViewModel<WeatherViewState, WeatherViewEvent>() {

    init {
        getWeatherByCountry("Paris")
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
            weatherRepository.fetchCurrentWeather(country = country).collect {
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
                                temperature = it.data.current.temp_c.toInt(),
                                country = it.data.location.country,
                                condition = it.data.current.condition.text
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