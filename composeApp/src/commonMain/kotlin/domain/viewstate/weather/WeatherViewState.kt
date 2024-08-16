package domain.viewstate.weather

import domain.viewstate.ViewState

data class WeatherViewState(
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = true,
    val country: String = "",
    val temperature: Int = 0,
    val condition: String = "",
) : ViewState