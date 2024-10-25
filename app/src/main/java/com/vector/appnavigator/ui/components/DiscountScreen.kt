package com.vector.appnavigator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vector.appnavigator.R
import com.vector.appnavigator.ui.theme.AppNavigatorTheme


@Composable
fun DiscountScreen(
    modifier: Modifier = Modifier
) {
    val priceField = remember { mutableStateOf("") }
    val discountField = remember { mutableStateOf("") }
    var currentPrice by remember { mutableDoubleStateOf(0.0) }
    var currentDiscount by remember { mutableDoubleStateOf(0.0) }
    var totalPrice by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFA3CECA))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Examen unidad I",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = priceField.value,
            onValueChange = {
                priceField.value = it
            },
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(text = "Price")
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.dolar),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = discountField.value,
            onValueChange = {
                if (it <= "99") {
                    discountField.value = it
                }
                currentDiscount = discountField.value.toDouble() / 100
                currentPrice = priceField.value.toDouble()
                totalPrice = calculateTotalPrice(currentPrice, currentDiscount)
            },
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(text = "Discount")
            },
            leadingIcon = {
                Text(text = "%", fontWeight = FontWeight.Bold)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,

            ) {
//            ElevatedButton(onClick = {
//                currentDiscount = discountField.value.toDouble() / 100
//                currentPrice = priceField.value.toDouble()
//                totalPrice = calculateTotalPrice(currentPrice, currentDiscount)
//            }) {
//                Text(
//                    text = "Calculate",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//            }

            ElevatedButton(onClick = {
                priceField.value = ""
                discountField.value = ""
                totalPrice = 0.0

            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
        Spacer(modifier = Modifier.height(140.dp))
        Text(
            text = "Your current discount is: ${if (discountField.value.isEmpty()) "0" else discountField.value}%",
            fontSize = 25.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Total price: $totalPrice",
            fontSize = 25.sp,
            fontStyle = FontStyle.Italic
        )

    }

}

private fun calculateTotalPrice(price: Double, discount: Double): Double {
    val discountAmount = price * discount
    return price - discountAmount
}

@Preview
@Composable
private fun MainScreenPreview() {
    AppNavigatorTheme {
        DiscountScreen()
    }
}