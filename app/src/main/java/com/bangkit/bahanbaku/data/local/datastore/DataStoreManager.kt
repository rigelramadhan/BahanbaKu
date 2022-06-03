package com.bangkit.bahanbaku.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("userpref")

class DataStoreManager @Inject constructor(@ApplicationContext context: Context) {
    private val dataStore = context.dataStore

    private val token = stringPreferencesKey("token")
    private val firstTime = booleanPreferencesKey("first_time")

    fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[token] ?: "null"
        }
    }

    fun isFirstTime(): Flow<Boolean> {
        return dataStore.data.map {
            it[firstTime] ?: true
        }
    }

    suspend fun setToken(token: String) {
        dataStore.edit {
            it[this.token] = token
        }
    }

    suspend fun setFirstTime(firstTime: Boolean) {
        dataStore.edit {
            it[this.firstTime] = firstTime
        }
    }

    suspend fun deleteToken() {
        dataStore.edit {
            it[token] = "null"
        }
    }
}