package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.pegbeer.pokeapp.core.R
import me.pegbeer.pokeapp.core.ui.theme.BlueGrey40
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme
import me.pegbeer.pokeapp.core.ui.theme.Typography


@Composable
fun ListTile(
    title:String,
    text:String,
    icon:ImageVector
){
    Row(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(60.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            imageVector = icon,
            modifier = Modifier.fillMaxHeight(),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = Typography.labelMedium.copy(color = BlueGrey40)
            )
            Text(
                text = text,
                style = Typography.bodySmall.copy(color = BlueGrey40)
            )
        }
    }
}

@Preview
@Composable
fun ListTilePreview(){
    PokeAppTheme {
        ListTile(
            title = "Title",
            text = "Text as label",
            icon = ImageVector.vectorResource(id = R.drawable.image_24)
        )
    }
}