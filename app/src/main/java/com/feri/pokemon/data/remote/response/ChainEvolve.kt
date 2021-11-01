package com.feri.pokemon.data.remote.response

import com.google.gson.annotations.SerializedName

data class ChainEvolve(

	@field:SerializedName("chain")
	val chain: Chain? = null,

	@field:SerializedName("baby_trigger_item")
	val babyTriggerItem: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null
)