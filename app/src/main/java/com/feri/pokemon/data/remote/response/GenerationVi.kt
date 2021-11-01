package com.feri.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenerationVi(

	@field:SerializedName("omegaruby-alphasapphire")
	val omegarubyAlphasapphire: OmegarubyAlphasapphire? = null,

	@field:SerializedName("x-y")
	val xY: XY? = null
)