package me.pegbeer.pokeapp.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.pegbeer.pokeapp.core.R
import me.pegbeer.pokeapp.core.ui.theme.Blue40
import me.pegbeer.pokeapp.core.ui.theme.BlueGrey40
import me.pegbeer.pokeapp.core.ui.theme.PokeAppTheme
import me.pegbeer.pokeapp.core.ui.theme.Typography
import me.pegbeer.pokeapp.core.ui.theme.White40

@Composable
fun SearchField(
    placeholder:String = "Buscar",
    trailingIcon: (@Composable () -> Unit)? = null,
){
    var text by rememberSaveable { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = {
                        text = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(color = White40, shape = RoundedCornerShape(30.dp))
            .border(width = 1.dp, color = BlueGrey40, shape = RoundedCornerShape(30.dp)),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp, 2.dp, 4.dp, 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ){
                    if (text.isEmpty()) {
                        Text(
                            placeholder,
                            style = Typography.titleSmall
                        )
                    }
                    innerTextField()
                }
                if(trailingIcon != null) trailingIcon()
                else Image(painter = painterResource(R.drawable.search), contentDescription = null)
            }
        }
    )
}

@Composable
@Preview
fun SearchFieldPreview(){
    PokeAppTheme {
        SearchField()
    }
}