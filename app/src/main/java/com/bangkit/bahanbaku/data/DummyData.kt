package com.bangkit.bahanbaku.data

import com.bangkit.bahanbaku.data.remote.response.*

object DummyData {

    fun recipeDummies(): RecipeResponse {
        val list = mutableListOf<RecipeEntity>()
        for (i in 0..20) {
            val recipe = RecipeEntity(
                "rec$i",
                "Recipe Number $i",
                "This is the description for the recipe number $i",
                "https://miro.medium.com/max/800/1*hFwwQAW45673VGKrMPE2qQ.png",
                i,
                4.6
            )
            list.add(recipe)
        }
        return RecipeResponse(list)
    }

    fun foodDummies(): FoodResponse {
        val list = mutableListOf<FoodEntity>()
        for (i in 0..20) {
            val food = FoodEntity(
                "foo$i",
                "Food Number $i",
                "This is the description for the food number $i",
                "https://miro.medium.com/max/800/1*hFwwQAW45673VGKrMPE2qQ.png"
            )
            list.add(food)
        }
        return FoodResponse(list)
    }

    fun profileDummies(): ProfileResponse {
        return ProfileResponse(
            "user12121",
            "User Name",
            "user@email.com",
            "https://miro.medium.com/max/800/1*hFwwQAW45673VGKrMPE2qQ.png"
        )
    }
}