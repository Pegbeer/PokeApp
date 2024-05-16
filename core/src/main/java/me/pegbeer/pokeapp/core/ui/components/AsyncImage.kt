package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    imageRequest: ImageRequest,
    dominantColorState: MutableState<Color>? = null,
    contentDescription:String? = null,
) {

    val painter = rememberAsyncImagePainter(imageRequest)

    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription
    )

    androidx.compose.runtime.SideEffect {
        if (painter.state is AsyncImagePainter.State.Success) {
            val bitmap = (painter.state as AsyncImagePainter.State.Success).result.drawable.toBitmap()
            Palette.from(bitmap).generate { palette ->
                dominantColorState?.value = Color(palette?.dominantSwatch?.rgb ?: Color.White.toArgb())
            }
        }
    }
}