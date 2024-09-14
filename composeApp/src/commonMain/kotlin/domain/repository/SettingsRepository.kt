package domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun saveCountry(country: String)

    suspend fun getCountry(): String

}