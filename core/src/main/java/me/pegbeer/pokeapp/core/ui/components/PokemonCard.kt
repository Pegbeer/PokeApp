package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import me.pegbeer.pokeapp.core.R
import me.pegbeer.pokeapp.core.model.Pokemon
import me.pegbeer.pokeapp.core.ui.theme.BlueGrey40
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme
import me.pegbeer.pokeapp.core.ui.theme.Typography
import me.pegbeer.pokeapp.core.ui.theme.White40

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonCard(
    pokemon:Pokemon?,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClick:() -> Unit
){
    with(sharedTransitionScope){
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(165.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = White40)
                .clickable(
                    enabled = true,
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                )
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
                        .fillMaxHeight(0.8f)
                        .align(Alignment.CenterHorizontally)
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-${pokemon?.getNumber()}"),
                            animatedVisibilityScope = animatedContentScope
                        ),
                    imageRequest = ImageRequest.Builder(LocalContext.current)
                        .data(pokemon?.getImageUrl())
                        .crossfade(true)
                        .allowHardware(false)
                        .placeholder(R.drawable.image_24)
                        .build(),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "text-${pokemon?.name}"),
                        animatedVisibilityScope = animatedContentScope
                    ),
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

}

@Composable
@Preview
fun PokemonCardPreview(){
    PokeAppTheme {

    }
}