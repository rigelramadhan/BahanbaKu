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

    suspend fun getFeaturedRecipe(): FeaturedRecipeResponse {
        val recipe = DummyData.recipeDummies().list[0]
        val featured = FeaturedRecipeEntity(
            recipe.id,
            recipe.name,
            recipe.description,
            recipe.photoUrl,
            recipe.servings,
            recipe.rating,
            "Author"
        )
        return FeaturedRecipeResponse(featured)
    }

    suspend fun getProfile(): ProfileResponse {
        return DummyData.profileDummies()
    }
}