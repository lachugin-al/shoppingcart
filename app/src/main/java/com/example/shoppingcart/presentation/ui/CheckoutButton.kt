package com.example.shoppingcart.presentation.ui

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.shoppingcart.presentation.ui.theme.ButtonYellow

/**
 * Кнопка для перехода к оплате с отображением общей стоимости товаров.
 *
 * @param totalPrice Общая стоимость товаров в корзине.
 * @param onCheckout Лямбда-функция, вызываемая при нажатии на кнопку.
 * @param modifier Модификатор для настройки внешнего вида кнопки.
 */
@Composable
fun CheckoutButton(
    totalPrice: Int,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onCheckout, // Обработчик нажатия
        colors = ButtonDefaults.buttonColors(containerColor = ButtonYellow),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp)
            .testTag("checkout_button")
            .semantics { contentDescription = "checkout_button" },
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("checkout_button_content")
                .semantics { contentDescription = "checkout_button_content" },
            contentAlignment = Alignment.Center
        ) {
            // Текст "Далее" в центре кнопки
            Text(
                text = "Далее",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("checkout_button_text")
                    .semantics { contentDescription = "checkout_button_text" }
            )
            // Текст с общей стоимостью в правой части кнопки
            Text(
                text = "$totalPrice ₽",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .testTag("checkout_button_price")
                    .semantics { contentDescription = "checkout_button_price" }
            )
        }
    }
}