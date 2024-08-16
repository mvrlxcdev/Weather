package features.screen.test

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.isLoading
import utils.onFailure
import utils.onSuccess

class TestScreenViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TestUiState())
    val uiState = _uiState.asStateFlow()

    fun getWeather(country: String = "Paris") = viewModelScope.launch {
        weatherRepository.fetchCurrentWeather(country = country).collect { weatherResult ->
            weatherResult.isLoading { isLoadin ->
                _uiState.update { it.copy(text = "loading") }
            }.onSuccess { weather ->
                _uiState.update { it.copy(text = weather.current.feelslike_c.toString()) }
            }.onFailure { error ->
                _uiState.update { it.copy(text = error.toString()) }
            }

        }
    }
}

data class TestUiState(
    val text: String = ""
)