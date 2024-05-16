package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme
import me.pegbeer.pokeapp.core.ui.theme.Typography
import kotlin.random.Random

@Composable
fun StatView(
    label:String,
    progress:Int,
    color: Color
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 2.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = Typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(.4f)
        )
        RoundedLinearProgressIndicator(
            progress = progress.toFloat() / 300,
            maxValue = .8f,
            progressColor = color,
            capColor = color.copy(alpha = .5f)
        )
        Text(
            text = String.format("%03d",progress),
            style = Typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}

@Preview
@Composable
fun StatViewPreview(){
    val hp = Random.nextInt(20,300)
    PokeAppTheme {
        StatView(
            label = "HP",
            progress = hp,
            color = Color.Green
        )
    }
}