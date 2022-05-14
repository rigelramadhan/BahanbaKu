package com.bangkit.bahanbaku.di

import android.content.Context
import com.bangkit.bahanbaku.data.local.room.FoodDatabase
import com.bangkit.bahanbaku.data.local.room.RecipeDatabase
import com.bangkit.bahanbaku.data.remote.retrofit.ApiConfig
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.data.repository.FoodRepository
import com.bangkit.bahanbaku.data.repository.RecipeRepository

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

    fun provideRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepository.getInstance(provideApiService(), provideRecipeDatabase(context))
    }

    fun provideFoodRepository(context: Context): FoodRepository {
        return FoodRepository.getInstance(provideApiService(), provideFoodDatabase(context))
    }
}