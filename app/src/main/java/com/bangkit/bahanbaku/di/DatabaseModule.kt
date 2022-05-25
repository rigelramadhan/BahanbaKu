package com.bangkit.bahanbaku.di

import android.content.Context
import com.bangkit.bahanbaku.data.local.room.FoodDatabase
import com.bangkit.bahanbaku.data.local.room.ProfileDatabase
import com.bangkit.bahanbaku.data.local.room.RecipeDatabase
import com.bangkit.bahanbaku.data.local.room.SupplierDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
}