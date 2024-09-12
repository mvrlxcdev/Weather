package data.di

import data.repository.SettingsRepositoryImpl
import data.repository.WeatherRepositoryImpl
import domain.repository.SettingsRepository
import domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(httpClient = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(dataStore = get()) }
}