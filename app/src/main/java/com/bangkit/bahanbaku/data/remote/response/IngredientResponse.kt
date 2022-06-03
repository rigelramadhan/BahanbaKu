package com.bangkit.bahanbaku.data.remote.response

import com.google.gson.annotations.SerializedName

data class IngredientResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: IngredientResults,

	@field:SerializedName("status")
	val status: Boolean
)

data class ProductsItem(

	@field:SerializedName("productName")
	val productName: String,

	@field:SerializedName("productPrice")
	val productPrice: Int
)

data class IngredientResults(

	@field:SerializedName("above")
	val above: List<AboveBelowItem>,

	@field:SerializedName("under")
	val under: List<AboveBelowItem>
)

data class AboveBelowItem(

	@field:SerializedName("suppliers")
	val suppliers: List<SuppliersItem>,

	@field:SerializedName("missingProduct")
	val missingProduct: List<String>,

	@field:SerializedName("totalPrice")
	val totalPrice: Int
)

data class SuppliersItem(

	@field:SerializedName("supplierName")
	val supplierName: String,

	@field:SerializedName("supplierId")
	val supplierId: String,

	@field:SerializedName("shippingCost")
	val shippingCost: Int,

	@field:SerializedName("supplierContact")
	val supplierContact: String,

	@field:SerializedName("products")
	val products: List<ProductsItem>
)
