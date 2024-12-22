package com.example.shoppingcart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.LazyColumn
import com.example.shoppingcart.presentation.viewmodel.CartViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppingcart.ui.CartHeader
import com.example.shoppingcart.ui.CartItemRow
import com.example.shoppingcart.ui.CheckoutButton

@Composable
fun CartScreen(viewModel: CartViewModel = hiltViewModel()) {
    val cartItems by viewModel.cartItems.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)  // Цвет фона экрана
    ) {
        // Верхняя часть экрана с заголовком и списком товаров
        Column(
            modifier = Modifier
                .weight(1f)  // Растягиваем на весь экран, чтобы нижний блок с кнопкой был прижат к низу
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)  // Закругление нижних углов
                )
                .padding(16.dp)
        ) {
            // Заголовок корзины
            CartHeader(
                itemCount = cartItems.size,
                onBackClick = { /* Обработка возврата */ },
                onDeleteCart = {
                    viewModel.clearCart() // Очищаем корзину через ViewModel
                }
            )

            // Список товаров
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)  // Список будет занимать оставшееся место
            ) {
                items(cartItems) { item ->
                    CartItemRow(
                        cartItem = item,
                        onCountChange = { newCount ->
                            val updatedItem = item.copy(count = newCount)
                            viewModel.updateCartItem(updatedItem) // Обновляем товар через ViewModel
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(36.dp))  // Отступ между списком и кнопкой

        // Блок с кнопкой "Далее", который всегда прижат к низу
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)  // Закругление верхних углов
                )
                .padding(16.dp)
        ) {
            CheckoutButton(
                totalPrice = totalPrice,
                onCheckout = { /* Переход к оплате */ }
            )
        }
    }
}