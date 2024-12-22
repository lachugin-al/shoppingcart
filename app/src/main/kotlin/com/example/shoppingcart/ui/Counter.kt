package com.example.shoppingcart.ui

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Компонент для изменения количества товара.
 *
 * @param count Текущее количество товара.
 * @param onCountChange Лямбда-функция, вызываемая при изменении количества товара. Передаёт новое значение в вызывающий компонент.
 */
@Composable
fun Counter(
    count: Int,
    onCountChange: (Int) -> Unit
) {
    // Контейнер для всего компонента
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(36.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 4.dp)
    ) {
        // Горизонтальная компоновка для кнопок и значения
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            // Кнопка уменьшения количества
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
            )

            // Кнопка увеличения количества
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
            )
        }
    }
}