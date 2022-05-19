package com.bangkit.bahanbaku.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.local.room.FoodDatabase
import com.bangkit.bahanbaku.data.local.room.ProfileDatabase
import com.bangkit.bahanbaku.data.local.room.RecipeDatabase
import com.bangkit.bahanbaku.data.remote.retrofit.ApiConfig
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.data.repository.FoodRepository
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.data.repository.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

private const val USER_PREFERENCES = "user_pref"
object AppModule {
    private fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    private fun provideRecipeDatabase(context: Context): RecipeDatabase {
        return RecipeDatabase.getInstance(context)
    }

    private fun provideFoodDatabase(context: Context): FoodDatabase {
        return FoodDatabase.getInstance(context)
    }

    private fun provideProfileDatabase(context: Context): ProfileDatabase {
        return ProfileDatabase.getInstance(context)
    }

    fun provideRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepository.getInstance(provideApiService(), provideRecipeDatabase(context))
    }

    fun provideFoodRepository(context: Context): FoodRepository {
        return FoodRepository.getInstance(provideApiService(), provideFoodDatabase(context))
    }

    fun provideProfileRepository(context: Context): ProfileRepository {
        return ProfileRepository.getInstance(provideApiService(), provideProfileDatabase(context))
    }

    fun providePreferencesDataStore(context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            migrations = listOf(SharedPreferencesMigration(context, USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(USER_PREFERENCES) })
    }

    fun provideUserPreferences(context: Context): UserPreferences {
        return UserPreferences.getInstance(providePreferencesDataStore(context))
    }
}