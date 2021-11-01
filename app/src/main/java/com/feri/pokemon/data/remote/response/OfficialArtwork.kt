package com.feri.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class OfficialArtwork(

	@field:SerializedName("front_default")
	val frontDefault: String? = null
)