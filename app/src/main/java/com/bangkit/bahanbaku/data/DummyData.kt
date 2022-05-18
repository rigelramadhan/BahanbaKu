package com.bangkit.bahanbaku.data

import com.bangkit.bahanbaku.data.remote.response.*

object DummyData {
//    val profiles = mutableListOf(profileDummy())
//    val profile = profileDummy()
//    val recipe = recipeDummies()
    val food = foodDummies()

//    fun profileDummy(): ProfileResponse {
//        return ProfileResponse(
//            ProfileEntity(
//                "2012-08-12",
//                "user",
//                Origin(127.0, -5.0),
//                "https://miro.medium.com/max/800/1*hFwwQAW45673VGKrMPE2qQ.png",
//                "this.is.token",
//                "2012-08-12",
//                listOf(2, 5, 11),
//                "testing",
//                listOf(ShippingItem(25000, 1)),
//                "John John",
//                1,
//                "test@test.com",
//                "2012-08-12"
//            )
//        )
//    }

//    fun recipeDummies(): RecipeResponse {
//        val list = mutableListOf<RecipeEntity>()
//        for (i in 0..20) {
//            val recipe = RecipeEntity(
//                "pQLi6pat",
//                "nasi goreng jawa",
//                3,
//                45,
//            )
//            list.add(recipe)
//        }
//        return RecipeResponse(list)
//    }

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