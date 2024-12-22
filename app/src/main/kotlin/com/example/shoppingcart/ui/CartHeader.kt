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
 * Над заголовком размещены:
 * - кнопка удаления корзины, которая вызывает onDeleteCart при нажатии;
 * - кнопка возврата к предыдущему Activity.
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
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад"
                )
            }
            IconButton(onClick = onDeleteCart) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить корзину"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.align(Alignment.Start)) {
            Text(
                text = "Корзина",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$itemCount блюда",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}