package com.feri.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class TypesItem(

	@field:SerializedName("slot")
	val slot: Int? = null,

	@field:SerializedName("type")
	val type: Type? = null
)