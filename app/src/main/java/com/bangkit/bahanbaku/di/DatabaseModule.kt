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
import com.bangkit.bahanbaku.data.local.room.SupplierDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_pref"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    private fun provideRecipeDatabase(@ApplicationContext context: Context): RecipeDatabase {
        return RecipeDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    private fun provideFoodDatabase(@ApplicationContext context: Context): FoodDatabase {
        return FoodDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    private fun provideProfileDatabase(@ApplicationContext context: Context): ProfileDatabase {
        return ProfileDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    private fun provideSupplierDatabase(@ApplicationContext context: Context): SupplierDatabase {
        return SupplierDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    private fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            migrations = listOf(SharedPreferencesMigration(context, USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(USER_PREFERENCES) })
    }

    @Provides
    @Singleton
    private fun provideUserPreferences(preferenceDataStore: DataStore<Preferences>): UserPreferences {
        return UserPreferences.getInstance(preferenceDataStore)
    }
}