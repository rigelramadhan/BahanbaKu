package com.bangkit.bahanbaku.data.remote.response

data class FeaturedRecipeResponse(
    val recipe: FeaturedRecipeEntity
)

data class FeaturedRecipeEntity(
    val id: String,

    val name: String,

    val description: String,

    val photoUrl: String,

    val servings: Int,

    val rating: Double,

    val author: String
)