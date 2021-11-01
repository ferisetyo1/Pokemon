package com.feri.pokemon.feature.pokemondetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.feri.pokemon.data.remote.response.ChainEvolve
import com.feri.pokemon.utils.Constant

@Composable
fun chainEvolve(pokemonEvolve: ChainEvolve) {
    Column {
        pokemonEvolve.chain?.let {
            Spacer(modifier = Modifier.height(8.dp))
            showEvolve(
                name = it.species?.name.orEmpty(),
                id = Constant.getLastNumber(it.species?.url),
                list = it.evolvesTo.orEmpty()
            )
        }
    }
}