package data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    override suspend fun saveCountry(country: String) {
        dataStore.edit { pref -> pref[stringPreferencesKey("country")] = country }
    }

    override suspend fun getCountry(): String {
        return dataStore.data.first()[stringPreferencesKey("country")] ?: "Moscow"
    }
}