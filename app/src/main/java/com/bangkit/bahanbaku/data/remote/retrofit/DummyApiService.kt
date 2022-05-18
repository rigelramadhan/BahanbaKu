package com.bangkit.bahanbaku.data.remote.retrofit

import com.bangkit.bahanbaku.data.DummyData
import com.bangkit.bahanbaku.data.remote.response.*
import java.util.*

class DummyApiService {
//    suspend fun register(
//        username: String,
//        email: String,
//        password: String
//    ): RegisterResponse {
//        val cal = Calendar.getInstance()
//        val date = "${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)}-${cal.get(Calendar.DATE)}"
//
//        val profile = ProfileEntity(
//            date,
//            "user",
//            Origin(0.0, 0.0),
//            "https://miro.medium.com/max/800/1*hFwwQAW45673VGKrMPE2qQ.png",
//            "this.is.token",
//            date,
//            listOf<Int>(),
//            password,
//            listOf<ShippingItem>(),
//            username,
//            DummyData.profiles.size,
//            email,
//            date
//        )
//
//        return RegisterResponse("Success")
//    }
//
//    suspend fun getRecipes(): RecipeResponse {
//        return DummyData.recipeDummies()
//    }
//
//    suspend fun getFoods(): FoodResponse {
//        return DummyData.foodDummies()
//    }
//
//    suspend fun getRecipe(id: Int): RecipeResponse {
//        return RecipeResponse(listOf(DummyData.recipeDummies().results[id]))
//    }
//
//    suspend fun getFeaturedRecipe(): FeaturedRecipeResponse {
//        val recipe = DummyData.recipeDummies().results[0]
//        return FeaturedRecipeResponse(recipe)
//    }
//
//    suspend fun getProfile(): ProfileResponse {
//        return DummyData.profileDummy()
//    }
}