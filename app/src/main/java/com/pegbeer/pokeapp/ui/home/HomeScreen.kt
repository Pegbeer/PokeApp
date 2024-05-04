package com.pegbeer.pokeapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.pegbeer.pokeapp.core.ui.components.SearchField
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
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ){
                Column {
                    Text(text = buildAnnotatedString{
                        withStyle(style = SpanStyle(fontSize = 20.sp)){
                            append("¡Hola,")
                        }
                        withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                            append(" bienvenido!")
                        }
                    })
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp))
                    SearchField()
                }
            }
        }
    }
}

const val Route = "home_screen"

@Composable
@Preview
fun HomeScreenPreview(){
    PokeAppTheme {
        HomeScreen()
    }
}