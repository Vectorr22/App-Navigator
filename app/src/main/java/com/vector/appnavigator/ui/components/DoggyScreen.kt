package com.vector.appnavigator.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vector.appnavigator.R
import com.vector.appnavigator.ui.theme.Purple80


@Composable
fun DoggyScreen(modifier: Modifier = Modifier) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember {
        mutableStateOf("")
    }
    val result = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Doggy Age Converter",
            fontSize = 36.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.doggy_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = text ,
            onValueChange ={text = it},
            //trailingIcon = ,
            placeholder = { Text(text = "Introduce your age")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(16.dp),

        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ElevatedButton(
                onClick = {
                    if(!text.contains(regex = Regex("[A-Za-z]"))){
                        var res = 0
                        res = text.toInt() * 7
                        result.value = "You have $res doggy ages!"
                    }
                    else{
                        Toast.makeText(
                            context,
                            "Your age is not correct!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    keyboardController?.hide()

                },
                colors = ButtonDefaults.buttonColors(containerColor = Purple80),
                modifier = Modifier.width(110.dp)
            ) {
                Text(text = "Calculate")
            }
            ElevatedButton(
                onClick = {
                    result.value = ""
                    text = ""

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD34C4C)),
                modifier = Modifier.width(110.dp)
            ) {
                Text(text = "Clear")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .width(250.dp)
                .border(
                    1.dp,
                    Color.Gray,
                    RoundedCornerShape(16.dp)
                )
        ){
            Text(
                text = result.value.ifEmpty { "Your doggy age" },
                color = Color.Gray,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    DoggyScreen()
}