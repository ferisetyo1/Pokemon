package com.feri.pokemon.feature.pokemonlist.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun showError(errorMsg: String = "Terjadi kesalahan internal.",retry:Boolean=true, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(8.dp))
    Card {
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), horizontalArrangement = Arrangement.Center
            ) {
                Text(text = errorMsg)
                Spacer(modifier = Modifier.width(16.dp))
                if (retry) {
                    Text(text = "Retry", fontWeight = FontWeight.W700)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "relead")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}