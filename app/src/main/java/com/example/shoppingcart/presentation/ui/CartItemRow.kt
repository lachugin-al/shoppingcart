package com.example.shoppingcart.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.shoppingcart.R
import com.example.shoppingcart.data.CartItem
import coil.compose.AsyncImage
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult

/**
 * Компонент для отображения строки товара в корзине.
 *
 * @param cartItem Объект [CartItem], представляющий данные о товаре.
 * @param onCountChange Лямбда-функция, вызываемая при изменении количества товара.
 * @param modifier Модификатор для настройки внешнего вида компонента.
 */
@Composable
fun CartItemRow(
    cartItem: CartItem, 
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("cart_item_row")
            .semantics { contentDescription = "cart_item_row_${cartItem.id}" },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Изображение товара
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cartItem.imageUrl)
                .error(R.drawable.error_image)
                .placeholder(R.drawable.loading_image)
                .listener(
                    onError = { _: ImageRequest, throwable: ErrorResult ->
                        Log.e("Coil", "Ошибка загрузки изображения: ${throwable.throwable.message}")
                    },
                    onSuccess = { _: ImageRequest, _: SuccessResult ->
                        Log.d("Coil", "Изображение успешно загружено")
                    }
                )
                .build(),
            contentDescription = cartItem.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .testTag("cart_item_image_${cartItem.id}")
                .semantics { contentDescription = "cart_item_image_${cartItem.id}" },
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier
                .width(16.dp)
                .semantics { contentDescription = "cart_item_spacer1_${cartItem.id}" }
        )

        // Информация о товаре
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 0.dp)
                .testTag("cart_item_info_${cartItem.id}")
                .semantics { contentDescription = "cart_item_info_${cartItem.id}" }
        ) {
            Text(
                text = cartItem.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .testTag("cart_item_name_${cartItem.id}")
                    .semantics { contentDescription = "cart_item_name_${cartItem.id}" }
            )
            Spacer(
                modifier = Modifier
                    .width(4.dp)
                    .semantics { contentDescription = "cart_item_info_spacer_${cartItem.id}" }
            )
            Text(
                text = "${cartItem.price} ${cartItem.currency}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .testTag("cart_item_price_${cartItem.id}")
                    .semantics { contentDescription = "cart_item_price_${cartItem.id}" }
            )
        }

        Spacer(
            modifier = Modifier
                .width(16.dp)
                .semantics { contentDescription = "cart_item_spacer2_${cartItem.id}" }
        )

        // Счётчик для изменения количества товара
        Counter(
            count = cartItem.count,
            onCountChange = onCountChange,
            itemId = cartItem.id.toString(),
            modifier = Modifier.testTag("cart_item_counter_${cartItem.id}")
        )
    }
}