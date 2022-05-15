package com.bangkit.bahanbaku.data.remote.response

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class FeaturedRecipeResponse(
    val recipe: RecipeEntity
)