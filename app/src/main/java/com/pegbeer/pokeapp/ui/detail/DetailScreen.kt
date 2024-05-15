package com.pegbeer.pokeapp.ui.detail

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.pegbeer.pokeapp.R
import com.pegbeer.pokeapp.ui.home.HomeViewModel
import me.pegbeer.pokeapp.core.Result
import me.pegbeer.pokeapp.core.model.PokemonDetail
import me.pegbeer.pokeapp.core.ui.components.AsyncImage
import me.pegbeer.pokeapp.core.ui.components.ListTile
import me.pegbeer.pokeapp.core.ui.components.Toolbar
import me.pegbeer.pokeapp.core.ui.theme.BlueGrey40
import kotlin.math.min

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: HomeViewModel
){
    with(sharedTransitionScope){

        val pokemon by viewModel.pokemonSelected.collectAsState()

        val id = pokemon?.getNumber()

        Log.w("DetailScreen", "DetailScreen: $pokemon")

        val state by viewModel.pokemonDetail.collectAsStateWithLifecycle(initialValue = Result.Loading())


        val pokemonInfo = if(state is Result.Success){
            (state as Result.Success<PokemonDetail>).data
        }else{
            null
        }

        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                when(state){
                    is Result.Loading ->{
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    is Result.Success ->{
                        Toolbar(
                            modifier = Modifier.sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "text-$id"),
                                animatedVisibilityScope = animatedContentScope
                            ),
                            title = pokemon?.capitalizedName() ?: "",
                            canNavigateBack = true,
                            suffix = pokemon?.getNumberAsString()
                        )
                        Column(
                            modifier = Modifier
                                .padding(16.dp,2.dp,16.dp,16.dp)
                        ) {
                            val dominantColorState = remember { mutableStateOf(Color.LightGray) }

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.3f),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .drawBehind {
                                            val width = this.size.width
                                            val height = this.size.height / 2

                                            val rectHeight = min(width, height)

                                            val x = 0f
                                            val y = size.height - rectHeight
                                            drawRoundRect(
                                                size = Size(width, rectHeight),
                                                color = dominantColorState.value,
                                                topLeft = Offset(x, y),
                                                cornerRadius = CornerRadius(15f)
                                            )
                                        }
                                        .fillMaxSize()
                                        .sharedElement(
                                            sharedTransitionScope.rememberSharedContentState(key = "image-$id"),
                                            animatedVisibilityScope = animatedContentScope
                                        ),
                                    imageRequest = ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(pokemon?.getImageUrl())
                                        .allowHardware(false)
                                        .placeholder(me.pegbeer.pokeapp.core.R.drawable.image_24)
                                        .build(),
                                    dominantColorState = dominantColorState,
                                    contentDescription = null
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .height(IntrinsicSize.Min)
                                    .fillMaxWidth()
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(15.dp)
                                    ),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ){
                               ListTile(
                                   title = pokemonInfo?.getWeightString() ?: "",
                                   text = "Peso",
                                   icon = ImageVector.vectorResource(id = R.drawable.weight)
                               )
                                VerticalDivider(
                                    modifier = Modifier.padding(0.dp,12.dp),
                                    thickness = 1.dp,
                                    color = BlueGrey40.copy(alpha = .3f)
                                )
                                ListTile(
                                    title = pokemonInfo?.getHeightString() ?: "",
                                    text = "Altura",
                                    icon = ImageVector.vectorResource(id = R.drawable.ruler)
                                )
                            }
                        }
                    }
                    else ->{
                        //FOR ERROR DISPLAY
                    }
                }


            }
        }
    }
}