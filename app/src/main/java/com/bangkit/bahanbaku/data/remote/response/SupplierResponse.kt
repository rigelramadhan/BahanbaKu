package com.bangkit.bahanbaku.data.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SupplierResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: SupplierResults
)

data class SupplierResults(

	@field:SerializedName("suppliers")
	val suppliers: List<SupplierEntity>
)

data class ProductItem(

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String
)

data class AddressObj(

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("district")
	val district: String,

	@field:SerializedName("sub-district")
	val subDistrict: String,

	@field:SerializedName("zip-code")
	val zipCode: String
)

@Entity(tableName = "supplier")
data class SupplierEntity(

	@field:SerializedName("product")
	val product: List<ProductItem>,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("origin")
	val origin: List<Double>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("address-obj")
	val addressObj: AddressObj,

	@PrimaryKey
	@field:SerializedName("id")
	val id: String
)
