package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeByIdResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: RecipeByIdResults
)

data class RecipeByIdResults(

	@field:SerializedName("recipe")
	val recipe: RecipeEntity
)
