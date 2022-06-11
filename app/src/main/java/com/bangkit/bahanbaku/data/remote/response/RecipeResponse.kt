package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: List<RecipeEntity>
)

data class RecipeEntity(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("steps")
	val steps: List<String>,
//
//	@field:SerializedName("tags")
//	val tags: List<String>,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("times")
	val times: Int,

	@field:SerializedName("servings")
	val servings: Int,

	@field:SerializedName("ingredients")
	val ingredients: List<String>,

	@field:SerializedName("totalViews")
	val totalViews: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
