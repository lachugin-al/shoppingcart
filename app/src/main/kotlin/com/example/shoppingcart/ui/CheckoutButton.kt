package com.example.shoppingcart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppingcart.ui.theme.ButtonYellow

/**
 * Кнопка для перехода к оплате с отображением общей стоимости товаров.
 */
@Composable
fun CheckoutButton(totalPrice: Int, onCheckout: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onCheckout,
        colors = ButtonDefaults.buttonColors(containerColor = ButtonYellow),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Далее",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
            Text(
                text = "$totalPrice ₽",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}