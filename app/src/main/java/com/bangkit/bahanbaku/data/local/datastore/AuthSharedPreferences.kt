package com.bangkit.bahanbaku.data.local.datastore

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthSharedPreferences @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        private const val PREF_NAME = "user_pref"
        private const val TOKEN = "token"
        private const val FIRST_TIME = "first_time"
    }

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    suspend fun setToken(token: String) {
        val editor = preferences.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getToken(): LiveData<String> {
        return MutableLiveData(preferences.getString(TOKEN, "null") ?: "null")
    }
}