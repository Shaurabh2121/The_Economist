package com.example.TheEconomist.Model

import com.google.gson.annotations.SerializedName

data class ResponseModel(

	@field:SerializedName("header")
	val header: String? = null,

	@field:SerializedName("details")
	val details: List<DetailsModel?>? = null,

	@field:SerializedName("id")
	val id: Int? = null
)