package com.bangkit.bahanbaku.data.remote.response

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class FeaturedRecipeResponse(
    val recipe: FeaturedRecipeEntity
)

data class FeaturedRecipeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "photoUrl")
    val photoUrl: String,

    @ColumnInfo(name = "servings")
    val servings: Int,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "author")
    val author: String
)