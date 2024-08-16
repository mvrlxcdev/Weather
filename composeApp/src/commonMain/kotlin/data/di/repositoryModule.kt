package data.di

import data.repository.WeatherRepositoryImpl
import domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(httpClient = get()) }
}