package com.bangkit.bahanbaku.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userpref")

class UserPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val appContext = context.applicationContext
    private val dataStore = appContext.dataStore

    fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[TOKEN] ?: "null"
        }
    }

    fun isFirstTime(): Flow<Boolean> {
        return dataStore.data.map {
            it[FIRST_TIME] ?: true
        }
    }

    suspend fun setToken(token: String) {
        dataStore.edit {
            it[TOKEN] = token
        }
    }

    suspend fun setFirstTime(firstTime: Boolean) {
        dataStore.edit {
            it[FIRST_TIME] = firstTime
        }
    }

    suspend fun deleteToken() {
        dataStore.edit {
            it[TOKEN] = "null"
        }
    }

    companion object {
        private val TOKEN = stringPreferencesKey("token")
        private val FIRST_TIME = booleanPreferencesKey("first_time")
    }
}