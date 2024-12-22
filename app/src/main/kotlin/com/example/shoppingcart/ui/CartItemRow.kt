package com.example.shoppingcart.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.shoppingcart.R
import coil.compose.AsyncImage
import com.example.shoppingcart.data.CartItem
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult

/**
 * Компонент для отображения строки товара в корзине.
 * Состоит из:
 * - изображение товара (с использованием Coil для загрузки);
 * - название;
 * - цена;
 * - счётчик для изменения количества товара.
 */
@Composable
fun CartItemRow(cartItem: CartItem, onCountChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cartItem.imageUrl)
                .error(R.drawable.error_image)
                .placeholder(R.drawable.loading_image)
                .listener(
                    onError = { _: ImageRequest, throwable: ErrorResult ->
                        Log.e("Coil", "Image loading error: ${throwable.throwable.message}")
                    },
                    onSuccess = { _: ImageRequest, _: SuccessResult ->
                        Log.d("Coil", "Image loaded successfully")
                    }
                )
                .build(),
            contentDescription = cartItem.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 0.dp)
        ) {
            Text(
                text = cartItem.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${cartItem.price} ${cartItem.currency}",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Counter(
            count = cartItem.count,
            onCountChange = onCountChange
        )
    }
}