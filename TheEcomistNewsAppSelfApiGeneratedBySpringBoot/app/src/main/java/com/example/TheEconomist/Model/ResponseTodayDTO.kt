package com.example.TheEconomist.Model

import com.google.gson.annotations.SerializedName

data class ResponseTodayDTO(

	@field:SerializedName("header")
	val header: String? = null,

	@field:SerializedName("details")
	val details: List<DetailsDTO?>? = null,

	@field:SerializedName("id")
	val id: Int? = null
)