package com.feri.pokemon.feature.pokemondetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.feri.pokemon.data.remote.response.EvolvesToItem
import com.feri.pokemon.utils.Constant

@Composable
fun showEvolve(name: String, id: String, list: List<EvolvesToItem>) {
    if (name.isNotEmpty()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = name.capitalize(Locale.current),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600
                    )
                    Image(
                        painter = rememberImagePainter(Constant.getPokemonImg(id)),
                        contentDescription = "",
                        modifier = Modifier.size(150.dp)
                    )
                }
            }
        }
    }
    list.forEach {
        showEvolve(
            name = it.species?.name.orEmpty(),
            Constant.getLastNumber(it.species?.url),
            list = it.evolvesTo.orEmpty()
        )
    }
}