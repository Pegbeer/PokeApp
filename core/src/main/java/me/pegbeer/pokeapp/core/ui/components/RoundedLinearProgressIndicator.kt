package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme

@Composable
fun RoundedLinearProgressIndicator(
    progress: Float,
    maxValue:Float = 1f,
    progressColor: Color = Color.Gray,
    capColor:Color = Color.LightGray,
) {

    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(
                durationMillis = 1500,
                easing = FastOutSlowInEasing
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(maxValue)
            .height(14.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(color = capColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedProgress.value)
                .fillMaxHeight()
                .clip(RoundedCornerShape(0.dp,26.dp,26.dp,0.dp))
                .background(color = progressColor)
        )
    }
}

@Preview
@Composable
fun RoundedLinearProgressIndicatorPreview(){
    PokeAppTheme {
        RoundedLinearProgressIndicator(progress = .5f)
    }
}