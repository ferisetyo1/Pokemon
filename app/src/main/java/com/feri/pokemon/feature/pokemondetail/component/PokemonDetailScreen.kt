package com.feri.pokemon.feature.pokemondetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.feri.pokemon.feature.pokemondetail.viewmodel.PokemonDetailViewModel
import com.feri.pokemon.feature.pokemonlist.component.showError
import com.feri.pokemon.utils.Constant

@Composable
fun PokemonDetailScreen(
    color: Int,
    pokemonName: String,
    pokemonID: String,
    navController: NavHostController,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getPokemonDetail(pokemonID)
        viewModel.getPokemonSpecies(pokemonID)
    }
    val page = remember { mutableStateOf("stat") }
    val pokemonStats by remember {
        viewModel.pokemonStat
    }
    val pokemonEvolve by remember {
        viewModel.pokemonChainEvolve
    }
    val isLoading by remember {
        viewModel.isLoading
    }
    val errorMsg by remember {
        viewModel.errorMsg
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(color),
                        MaterialTheme.colors.surface
                    )
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = rememberImagePainter(Constant.getPokemonImg(pokemonID)),
            contentDescription = pokemonName,
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = pokemonName.capitalize(Locale.current),
            fontWeight = FontWeight.W700,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Spacer(modifier = Modifier.width(16.dp))
            if (page.value == "stat") {
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Stats")

                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(
                    onClick = { page.value = "evo" },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Evolution")
                }
            } else {
                OutlinedButton(
                    onClick = { page.value = "stat" },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Stats")

                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Evolution")
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
        }

        if (page.value == "stat")
            pokemonStats?.let { statsPokemon(it) }
        if (page.value == "evo")
            pokemonEvolve?.let { chainEvolve(it) }
        println("pokemonEvolve:$pokemonEvolve")

        if (isLoading) showLoading()

        if (errorMsg.isNotEmpty()) showError(errorMsg,false)
    }

}

