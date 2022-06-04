package com.bangkit.bahanbaku.data.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RecipeResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: List<RecipeEntity>
)

@Entity(tableName = "recipe")
data class RecipeEntity(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("servings")
	val servings: Int,

	@field:SerializedName("times")
	val times: Int,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("ingredients")
	val ingredients: List<String>,

	@field:SerializedName("description")
	val description: String,

	@PrimaryKey
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("steps")
	val steps: List<String>,

	@field:SerializedName("tags")
	val tags: List<String>
)

data class RecipeResults(

	@field:SerializedName("recipes")
	val recipes: List<RecipeEntity>
)
