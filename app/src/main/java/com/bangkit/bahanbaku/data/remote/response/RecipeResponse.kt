package com.bangkit.bahanbaku.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeResponse(

	@field:SerializedName("result")
	val result: List<RecipeEntity>
) : Parcelable

@Entity(tableName = "recipe")
@Parcelize
data class RecipeEntity(

	@field:SerializedName("images")
	val images: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("steps")
	val steps: List<String>,

	@field:SerializedName("tags")
	val tags: List<String>,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("servings")
	val servings: Int,

	@field:SerializedName("times")
	val times: Int,

	@field:SerializedName("ingredients")
	val ingredients: List<String>,

	@field:SerializedName("totalViews")
	val totalViews: Int,

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("key")
	val key: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String,

	@field:SerializedName("desc")
	val desc: String
) : Parcelable
