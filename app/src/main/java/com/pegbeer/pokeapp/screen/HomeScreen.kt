package com.pegbeer.pokeapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.pegbeer.pokeapp.core.ui.components.Toolbar
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Toolbar(
                title = "Pokédex",
                canNavigateBack = false
            )
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview(){
    PokeAppTheme {
        HomeScreen()
    }
}