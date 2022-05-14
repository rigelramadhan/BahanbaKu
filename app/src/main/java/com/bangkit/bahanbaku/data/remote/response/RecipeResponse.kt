package com.bangkit.bahanbaku.data.remote.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class RecipeResponse(
    val list: List<RecipeEntity>
)

@Entity(tableName = "recipe")
data class RecipeEntity(
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
    val rating: Double
)
