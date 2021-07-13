package uz.suhrob.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "news-pref")

class AppPrefs(
    private val context: Context,
) {
    val isNightTheme: Flow<Boolean> = context.dataStore.data
        .map { it[NIGHT_THEME] ?: false }

    suspend fun setNightTheme(isNight: Boolean) {
        context.dataStore.edit { pref ->
            pref[NIGHT_THEME] = isNight
        }
    }

    val updateInterval: Flow<Int?> = context.dataStore.data
        .map { it[UPDATE_INTERVAL] }

    suspend fun setUpdateInterval(interval: Int) {
        context.dataStore.edit { pref ->
            pref[UPDATE_INTERVAL] = interval
        }
    }

    companion object {
        private val NIGHT_THEME = booleanPreferencesKey(name = "night_theme")
        private val UPDATE_INTERVAL = intPreferencesKey(name = "update_interval")
    }
}