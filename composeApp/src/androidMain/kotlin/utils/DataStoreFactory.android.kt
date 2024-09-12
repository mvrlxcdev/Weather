package utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual class DatastoreFactory(private val context: Context) {
    actual fun createDatastore(): DataStore<Preferences> {
        return initDataStore {
            context.filesDir.resolve(dataStoreFileName).absolutePath
        }
    }
}

