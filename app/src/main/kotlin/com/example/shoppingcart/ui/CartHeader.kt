package com.example.shoppingcart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Компонент, который отображает заголовок экрана корзины и количество товаров в корзине.
 *
 * @param itemCount Количество товаров в корзине.
 * @param onDeleteCart Лямбда-функция, вызываемая при нажатии на кнопку удаления корзины.
 * @param onBackClick Лямбда-функция, вызываемая при нажатии на кнопку возврата.
 */
@Composable
fun CartHeader(
    itemCount: Int,
    onDeleteCart: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка возврата
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад"
                )
            }
            // Кнопка удаления корзины
            IconButton(onClick = onDeleteCart) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить корзину"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Заголовок и количество товаров
        Column(modifier = Modifier.align(Alignment.Start)) {
            Text(
                text = "Корзина",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$itemCount ${getItemCountLabel(itemCount)}",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

/**
 * Возвращает корректное слово "блюдо" в зависимости от количества.
 *
 * @param count Количество товаров.
 * @return Слово в правильной форме.
 */
fun getItemCountLabel(count: Int): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> "блюдо"
        count % 10 in 2..4 && (count % 100 !in 12..14) -> "блюда"
        else -> "блюд"
    }
}