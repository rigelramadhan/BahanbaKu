package com.bangkit.bahanbaku.data.remote.response

import androidx.room.Entity

data class FoodResponse(
    val list: List<FoodEntity>
)

@Entity(tableName = "food")
data class FoodEntity(
    val id: String,

    val name: String,

    val description: String
)
