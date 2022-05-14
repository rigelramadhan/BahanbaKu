package com.bangkit.bahanbaku.data.remote.response

import androidx.room.Entity

data class RecipeResponse(
    val list: List<RecipeEntity>
)

@Entity(tableName = "recipe")
data class RecipeEntity(
    val id: String,

    val name: String,

    val description: String,

    val servings: Int,

    val rating: Double
)
