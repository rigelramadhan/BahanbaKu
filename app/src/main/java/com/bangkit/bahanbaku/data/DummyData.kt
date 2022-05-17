package com.bangkit.bahanbaku.data

import com.bangkit.bahanbaku.data.remote.response.*

object DummyData {

    fun profileDummy(): ProfileResponse {
        return ProfileResponse(ProfileEntity(
            "2012-08-12",
            "user",
            Origin(127.0, -5.0),
            "https://miro.medium.com/max/800/1*hFwwQAW45673VGKrMPE2qQ.png",
            "this.is.token",
            "2012-08-12",
            listOf(2, 5, 11),
            "thisispassword",
            listOf(ShippingItem(25000, 1)),
            "John John",
            1,
            "john@email.com",
            "2012-08-12"
        ))
    }

    fun recipeDummies(): RecipeResponse {
        val list = mutableListOf<RecipeEntity>()
        for (i in 0..20) {
            val recipe = RecipeEntity(
                "https://miro.medium.com/max/800/1*hFwwQAW45673VGKrMPE2qQ.png",
                "John John",
                4.7,
                "Recipe$i",
                listOf("Steps$i", "Steps${i+1}", "Steps${i+2}", "Steps${i+3}"),
                listOf("Tags$i", "Tags${i+1}", "Tags${i+2}", "Tags${i+3}"),
                "2012-08-12",
                i,
                15*i,
                listOf("Ingredient$i", "Ingredient${i+1}", "Ingredient${i+2}", "Ingredient${i+3}"),
                2*i,
                i,
                "i$i",
                "2013-08-12",
                "This is the description for the recipe number $i"
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
}