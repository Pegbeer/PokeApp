package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonLayout(
    isLoading:Boolean,
    content: @Composable () -> Unit
){
    Box(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
            .drawWithContent {
                if (isLoading) {
                    drawShimmerEffect()
                }else{
                    drawContent()
                }
            }
    ){
        content()
    }
}

private fun ContentDrawScope.drawShimmerEffect() {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.2f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.2f)
        ),
        start = Offset(0f,0f),
        end = Offset(size.width,size.height)
    )
    drawRect(brush = gradientBrush)
}

@Preview
@Composable
fun PreviewShimmerLayout() {
    SkeletonLayout(isLoading = true) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Blue)
        )
    }
}