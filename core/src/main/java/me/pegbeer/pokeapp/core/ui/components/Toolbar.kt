package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import me.pegbeer.pokeapp.core.R
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    actions: @Composable () -> Unit = {}
){
    Surface {
        if(canNavigateBack){
            TopAppBar(
                title = { Text(title) },
                actions = { actions() },
                navigationIcon = {
                    IconButton(onClick = navigateUp){
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.pokemon),
                            contentDescription = null
                        )
                    }
                },
                modifier = modifier
            )
        }else{
            TopAppBar(
                title = { Text(title) },
                actions = { actions() },
                navigationIcon = {
                    IconButton(onClick = navigateUp){
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.pokemon),
                            contentDescription = null
                        )
                    }
                },
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
fun ToolbarPreview(){
    PokeAppTheme {
        Toolbar(title = "Pokemon", canNavigateBack = false)
    }
}