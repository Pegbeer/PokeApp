package com.pegbeer.pokeapp.ui.detail

import android.graphics.PointF
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.request.ImageRequest
import com.pegbeer.pokeapp.R
import com.pegbeer.pokeapp.ui.home.HomeViewModel
import me.pegbeer.pokeapp.core.Result
import me.pegbeer.pokeapp.core.model.Pokemon
import me.pegbeer.pokeapp.core.model.PokemonDetail
import me.pegbeer.pokeapp.core.ui.components.AsyncImage
import me.pegbeer.pokeapp.core.ui.components.SkeletonLayout
import me.pegbeer.pokeapp.core.ui.components.Toolbar
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme
import org.koin.androidx.compose.koinViewModel
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
                                            val height = this.size.height * 2f / 3f

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