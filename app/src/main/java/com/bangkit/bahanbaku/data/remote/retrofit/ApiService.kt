package com.bangkit.bahanbaku.data.remote.retrofit

import com.bangkit.bahanbaku.data.DummyData
import com.bangkit.bahanbaku.data.remote.response.*

class ApiService {
    suspend fun getRecipes(): RecipeResponse {
        return DummyData.recipeDummies()
    }

    suspend fun getFoods(): FoodResponse {
        return DummyData.foodDummies()
    }

    suspend fun getRecipe(id: Int): RecipeResponse {
        return RecipeResponse(listOf(DummyData.recipeDummies().result[id]))
    }

    suspend fun getFeaturedRecipe(): FeaturedRecipeResponse {
        val recipe = DummyData.recipeDummies().result[0]
        return FeaturedRecipeResponse(recipe)
    }

    suspend fun getProfile(): ProfileResponse {
        return DummyData.profileDummy()
    }
}