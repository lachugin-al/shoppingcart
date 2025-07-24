package com.example.shoppingcart.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Компонент для изменения количества товара.
 *
 * @param count Текущее количество товара.
 * @param onCountChange Лямбда-функция, вызываемая при изменении количества товара. Передаёт новое значение в вызывающий компонент.
 * @param modifier Модификатор для настройки внешнего вида компонента.
 * @param itemId Идентификатор товара, используется для создания уникальных testTag.
 */
@Composable
fun Counter(
    count: Int,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemId: String = ""
) {
    // Контейнер для всего компонента
    Box(
        modifier = modifier
            .width(100.dp)
            .height(36.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 4.dp)
            .testTag("counter_container${ if (itemId.isNotEmpty()) "_$itemId" else "" }")
            .semantics { contentDescription = "counter_container${ if (itemId.isNotEmpty()) "_$itemId" else "" }" }
    ) {
        // Горизонтальная компоновка для кнопок и значения
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .testTag("counter_row${ if (itemId.isNotEmpty()) "_$itemId" else "" }")
                .semantics { contentDescription = "counter_row${ if (itemId.isNotEmpty()) "_$itemId" else "" }" }
        ) {
            // Кнопка уменьшения количества, с отключением кнопки если значение минимально
            Text(
                text = "-",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .clickable { if (count > 1) onCountChange(count - 1) }
                    .align(Alignment.CenterVertically)
                    .padding(4.dp)
                    .testTag("counter_minus_button${ if (itemId.isNotEmpty()) "_$itemId" else "" }")
                    .semantics { contentDescription = "counter_minus_button${ if (itemId.isNotEmpty()) "_$itemId" else "" }" },
                color = if (count > 1) Color.Black else Color.Gray
            )

            // Текущее количество товара
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 8.dp)
                    .testTag("counter_value${ if (itemId.isNotEmpty()) "_$itemId" else "" }")
                    .semantics { contentDescription = "counter_value${ if (itemId.isNotEmpty()) "_$itemId" else "" }" }
            )

            // Кнопка увеличения количества, отключение кнопки если значение максимально
            Text(
                text = "+",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .clickable { if (count < 99) onCountChange(count + 1) }
                    .align(Alignment.CenterVertically)
                    .padding(4.dp)
                    .testTag("counter_plus_button${ if (itemId.isNotEmpty()) "_$itemId" else "" }")
                    .semantics { contentDescription = "counter_plus_button${ if (itemId.isNotEmpty()) "_$itemId" else "" }" },
                color = if (count < 99) Color.Black else Color.Gray
            )
        }
    }
}