package com.feri.pokemon.feature.pokemonlist.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.feri.pokemon.feature.pokemonlist.viewmodel.PokemonListViewModel

@Composable
fun ListSection(
    navController: NavHostController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList by remember { viewModel.pokemonList }
    val errorMsg by remember { viewModel.errorMsg }
    val isLoading by remember { viewModel.isLoading }
    val isMaxPage by remember { viewModel.isMaxPage }


    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemcount = pokemonList.size
        items(itemcount) {
            if (it == 0) Spacer(modifier = Modifier.height(8.dp))
            if (!isMaxPage && it >= itemcount - 1) {
                LaunchedEffect(key1 = true) {
                    viewModel.getListPokemon()
                }
            }
            Item(item = pokemonList[it], navController, viewModel)
        }
    }

    if (errorMsg.isNotEmpty()) {
        showError(errorMsg)
    }

    if (isLoading) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.scale(0.5f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}