package com.feri.pokemon.feature.pokemondetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.feri.pokemon.data.remote.response.Pokemon

@Composable
fun statsPokemon(pokemon: Pokemon) {
    Column(modifier = Modifier.padding(16.dp)) {
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
            Column(modifier = Modifier.padding(8.dp)) {
                pokemon.height?.let {
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W700)) {
                            append("Tinggi\t: ")
                        }
                        append(it.toString())
                    })
                }
                pokemon.stats?.let {
                    it.forEach {
                        Text(text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.W700)) {
                                append(
                                    "${
                                        it.stat?.name.orEmpty().replace("-", " ")
                                            .capitalize(
                                                Locale.current
                                            )
                                    }\t: "
                                )
                            }
                            it.baseStat?.let {
                                append(it.toString())
                            }
                        })
                    }
                }

            }
        }
    }
}