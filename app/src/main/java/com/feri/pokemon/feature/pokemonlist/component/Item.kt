package com.feri.pokemon.feature.pokemonlist.component

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.feri.pokemon.data.remote.response.PokemonList
import com.feri.pokemon.feature.pokemonlist.viewmodel.PokemonListViewModel
import com.feri.pokemon.utils.Constant
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun Item(
    item: PokemonList.ResultsItem,
    navController: NavHostController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {

    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    val painter = rememberImagePainter(Constant.getPokemonImg(item.getIdNumber()))
    when (painter.state) {
        is ImagePainter.State.Success -> {
            LaunchedEffect(key1 = painter) {
                launch {
                    val image = painter.imageLoader.execute(painter.request).drawable
                    viewModel.calcDominantColor(image!!) {
                        dominantColor = it
                    }
                }
            }
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(route = "${Constant.Route.pokemonDetailScreen}/${dominantColor.toArgb()}/${item.name}/${item.getIdNumber()}") },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            defaultDominantColor,
                            dominantColor
                        )
                    )
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = item.name.orEmpty(),
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.name.orEmpty().capitalize(Locale.current),
                fontWeight = FontWeight.W700,
                fontSize = 18.sp
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}