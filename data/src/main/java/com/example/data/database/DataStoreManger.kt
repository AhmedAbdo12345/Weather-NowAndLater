package com.example.data.database

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val TAG = "DataStoreManger"
val Context.dataStore by preferencesDataStore(name = "city_prefs")
val CITY_NAME_KEY = stringPreferencesKey("city_name")

class DataStoreManger @Inject constructor(val context: Context) {
    private val dataStore = context.dataStore

      suspend fun getCity(): String? {
          val preferences = dataStore.data.first()
          return preferences[CITY_NAME_KEY]
    }

    suspend fun saveCity(city: String): Boolean {
        return try {
            dataStore.edit { preferences ->
                preferences[CITY_NAME_KEY] = city
            }
            true
        } catch (e: Exception) {
            Log.d(TAG, "saveCity: ${e.message}")
          false
        }
       
    }
}