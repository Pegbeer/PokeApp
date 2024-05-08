package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.pegbeer.pokeapp.core.R
import me.pegbeer.pokeapp.core.model.Pokemon
import me.pegbeer.pokeapp.core.ui.theme.BlueGrey40
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme
import me.pegbeer.pokeapp.core.ui.theme.Typography
import me.pegbeer.pokeapp.core.ui.theme.White40

@Composable
fun PokemonCard(
    pokemon:Pokemon?,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = White40)
            .padding(15.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = pokemon?.getNumberAsString() ?: "#000",
                modifier = Modifier.align(Alignment.End),
                style = Typography.titleSmall
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .align(Alignment.CenterHorizontally),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon?.getImageUrl())
                    .crossfade(true)
                    .placeholder(R.drawable.image_24)
                    .build(),
                contentDescription = null
            )
            Text(
                text = pokemon?.capitalizedName() ?: "????",
                style = Typography.titleMedium.copy(
                    color = BlueGrey40,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 26.sp
                )
            )
        }
    }
}

@Composable
@Preview
fun PokemonCardPreview(){
    PokeAppTheme {
        PokemonCard(Pokemon(1,"bulbasaur","https://pokeapi.co/api/v2/pokemon/1/"))
    }
}