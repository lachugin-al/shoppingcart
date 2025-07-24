package com.example.shoppingcart.presentation.ui

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Компонент, который отображает заголовок экрана корзины и количество товаров в корзине.
 *
 * @param itemCount Количество товаров в корзине.
 * @param onDeleteCart Лямбда-функция, вызываемая при нажатии на кнопку удаления корзины.
 * @param onBackClick Лямбда-функция, вызываемая при нажатии на кнопку возврата.
 * @param modifier Модификатор для настройки внешнего вида компонента.
 */
@Composable
fun CartHeader(
    itemCount: Int,
    onDeleteCart: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("cart_header")
            .semantics { contentDescription = "cart_header" }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("cart_header_buttons_row")
                .semantics { contentDescription = "cart_header_buttons_row" },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка возврата
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .testTag("cart_back_button")
                    .semantics { contentDescription = "cart_back_button" }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад"
                )
            }
            // Кнопка удаления корзины
            IconButton(
                onClick = onDeleteCart,
                modifier = Modifier
                    .testTag("cart_delete_button")
                    .semantics { contentDescription = "cart_delete_button" }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить корзину"
                )
            }
        }

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .testTag("cart_header_spacer")
                .semantics { contentDescription = "cart_header_spacer" }
        )

        // Заголовок и количество товаров
        Column(
            modifier = Modifier
                .align(Alignment.Start)
                .testTag("cart_header_title_column")
                .semantics { contentDescription = "cart_header_title_column" }
        ) {
            Text(
                text = "Корзина",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .testTag("cart_title")
                    .semantics { contentDescription = "cart_title" }
            )
            Text(
                text = "$itemCount ${getItemCountLabel(itemCount)}",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .testTag("cart_item_count")
                    .semantics { contentDescription = "cart_item_count" }
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