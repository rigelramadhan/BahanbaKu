package com.bangkit.bahanbaku.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeResponse(

	@field:SerializedName("results")
	val results: List<RecipeEntity>
) : Parcelable

@Parcelize
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

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("steps")
	val steps: List<String>,

	@field:SerializedName("tags")
	val tags: List<String>
) : Parcelable
