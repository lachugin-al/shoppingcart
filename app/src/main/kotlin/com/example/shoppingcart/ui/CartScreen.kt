package com.example.shoppingcart.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.shoppingcart.data.CartInteractor
import com.example.shoppingcart.data.CartItem
import kotlinx.coroutines.launch

/**
 * Это основная функция, отображающая экран корзины.
 * Загружает элементы корзины с помощью предоставленного CartInteractor и отображает их.
 * Экран состоит из заголовка, списка товаров и кнопки перехода к оплате.
 * Для управления состоянием используется remember и mutableStateOf.
 */
@Composable
fun CartScreen(cartInteractor: CartInteractor) {
    val cartItemsState = remember { mutableStateOf<List<CartItem>>(emptyList()) }
    val totalPriceState = remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        cartItemsState.value = cartInteractor.getCartItems()
        calculateTotalPrice(cartItemsState.value, totalPriceState)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(16.dp)
        ) {
            CartHeader(
                itemCount = cartItemsState.value.size,
                onBackClick = {
                    (context as? Activity)?.finish()
                },
                onDeleteCart = {
                    coroutineScope.launch {
                        cartInteractor.deleteCart()
                        cartItemsState.value = emptyList()
                        totalPriceState.value = 0
                    }
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(cartItemsState.value) { item ->
                    CartItemRow(
                        cartItem = item,
                        onCountChange = { newCount ->
                            coroutineScope.launch {
                                val updatedItem = item.copy(count = newCount)
                                cartInteractor.updateCartItem(updatedItem)
                                updateCartItems(cartInteractor, cartItemsState, totalPriceState)
                            }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            CheckoutButton(
                totalPrice = totalPriceState.value,
                onCheckout = { /* Обработка перехода к оплате */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

/**
 * Эта функция выполняет обновление состояния списка товаров в корзине, запрашивая
 * актуальные данные у переданного [cartInteractor]. После получения обновленного списка
 * товаров она вызывает функцию [calculateTotalPrice], чтобы пересчитать и обновить
 * общую стоимость товаров в корзине.
 *
 * @param cartInteractor Интерфейс для взаимодействия с корзиной через который выполняются операции с товарами;
 * @param cartItemsState Состояние, содержащее список товаров в корзине которое будет обновлено;
 * @param totalPriceState Состояние, содержащее общую стоимость товаров которое будет обновлено.
 */
private suspend fun updateCartItems(
    cartInteractor: CartInteractor,
    cartItemsState: MutableState<List<CartItem>>,
    totalPriceState: MutableState<Int>
) {
    cartItemsState.value = cartInteractor.getCartItems()
    calculateTotalPrice(cartItemsState.value, totalPriceState)
}

/**
 * Эта функция принимает список товаров и вычисляет их общую стоимость умножая цену каждого товара на его количество.
 * Результат сохраняется в [totalPriceState].
 * @param cartItems Список товаров в корзине, для которых необходимо вычислить общую стоимость;
 * @param totalPriceState Состояние в которое будет сохранена полученная общая стоимость.
 */
private fun calculateTotalPrice(
    cartItems: List<CartItem>,
    totalPriceState: MutableState<Int>
) {
    totalPriceState.value = cartItems.sumOf { it.price * it.count }
}