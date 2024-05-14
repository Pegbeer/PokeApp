@file:OptIn(ExperimentalMaterial3Api::class)

package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import me.pegbeer.pokeapp.core.R
import me.pegbeer.pokeapp.core.ui.theme.Blue40
import me.pegbeer.pokeapp.core.ui.theme.BlueGrey40
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme
import me.pegbeer.pokeapp.core.ui.theme.Typography


@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    actions: @Composable () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    suffix:String? = null
){
    Surface(
        Modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            if(canNavigateBack){
                TopAppBar(
                    modifier = modifier,
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(title, color = BlueGrey40, style = Typography.titleMedium)
                            Text(suffix ?: "#???", style = Typography.headlineSmall)
                        }
                    },
                    actions = { actions() },
                    navigationIcon = {
                        IconButton(onClick = navigateUp){
                            Icon(
                                painter = painterResource(R.drawable.left_arrow),
                                contentDescription = null,
                                tint = BlueGrey40
                            )
                        }
                    }
                )
            }else{
                TopAppBar(
                    modifier = modifier,
                    title = { Text(
                        title,
                        color = Blue40,
                        style = Typography.titleLarge
                    )},
                    actions = { actions() },
                    navigationIcon = {
                        IconButton(onClick = navigateUp){
                            Image(
                                painter = painterResource(R.drawable.pokemon),
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun ToolbarCanGoBackPreview(){
    PokeAppTheme {
        Toolbar(
            title = "Charizard",
            canNavigateBack = true
        )
    }
}

@Preview
@Composable
fun ToolbarCannotGoBackPreview(){
    PokeAppTheme {
        Toolbar(
            title = "Pokédex",
            canNavigateBack = false
        )
    }
}