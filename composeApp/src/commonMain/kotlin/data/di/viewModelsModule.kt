package data.di

import features.screen.test.TestScreenViewModel
import features.screen.weather.WeatherViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelsModule = module {
    viewModelOf(::TestScreenViewModel)
    viewModelOf(::WeatherViewModel)
}