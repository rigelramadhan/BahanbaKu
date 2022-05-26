package com.bangkit.bahanbaku.data.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class IngredientResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@field:SerializedName("shippingPrice")
	val shippingPrice: List<ShippingPriceItem>,

	@field:SerializedName("totalPrice")
	val totalPrice: Int,

	@field:SerializedName("ingredients")
	val ingredients: List<IngredientsItem>
)

data class IngredientsItem(

	@field:SerializedName("supplierName")
	val supplierName: String,

	@field:SerializedName("supplierId")
	val supplierId: String,

	@field:SerializedName("ingredientName")
	val ingredientName: String,

	@field:SerializedName("ingredientPrice")
	val ingredientPrice: Int
)

data class ShippingPriceItem(

	@field:SerializedName("shippingCost")
	val shippingCost: Int,

	@field:SerializedName("id")
	val id: String
)
