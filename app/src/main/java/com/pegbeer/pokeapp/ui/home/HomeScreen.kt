package com.pegbeer.pokeapp.ui.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.pegbeer.pokeapp.ui.util.itemsPaging
import me.pegbeer.pokeapp.core.model.Pokemon
import me.pegbeer.pokeapp.core.ui.components.PokemonCard
import me.pegbeer.pokeapp.core.ui.components.SearchField
import me.pegbeer.pokeapp.core.ui.components.Toolbar
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    pokemons:LazyPagingItems<Pokemon>
){
    val context = LocalContext.current
    LaunchedEffect(key1 = pokemons.loadState) {
        if(pokemons.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (pokemons.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }


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
                Column(
                    Modifier.fillMaxSize()
                ) {
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
                        .height(20.dp))
                    SearchField()
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp))

                    val gridState = rememberLazyGridState()

                    if(pokemons.loadState.refresh is LoadState.Loading){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }else{
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            state = gridState,
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            items(pokemons.itemCount){ pokemon ->
                                PokemonCard(pokemon = pokemons[pokemon])
                            }
                            item {
                                if(pokemons.loadState.append is LoadState.Loading){
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

const val TAG = "HomeScreen"

@Composable
@Preview
fun HomeScreenPreview(){
    PokeAppTheme {

    }
}