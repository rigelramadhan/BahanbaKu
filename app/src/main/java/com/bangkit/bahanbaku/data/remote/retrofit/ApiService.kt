package com.bangkit.bahanbaku.data.remote.retrofit

import com.bangkit.bahanbaku.data.DummyData
import com.bangkit.bahanbaku.data.remote.response.FoodResponse
import com.bangkit.bahanbaku.data.remote.response.ProfileResponse
import com.bangkit.bahanbaku.data.remote.response.RecipeResponse

class ApiService {
    suspend fun getRecipes(): RecipeResponse {
        return DummyData.recipeDummies()
    }

    suspend fun getFoods(): FoodResponse {
        return DummyData.foodDummies()
    }

    suspend fun getProfile(): ProfileResponse {
        return DummyData.profileDummies()
    }
}