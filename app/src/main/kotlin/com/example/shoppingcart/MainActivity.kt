package com.example.shoppingcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.shoppingcart.presentation.ui.CartScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Главная активность приложения.
 * Отвечает за отображение экрана корзины.
 *
 * Использует Hilt для автоматической инъекции зависимостей.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Метод вызывается при создании активности.
     *
     * В данном методе устанавливается основное содержимое экрана
     * с использованием Jetpack Compose.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Устанавливаем содержимое экрана
        setContent {
            CartScreen() // ViewModel будет инжектирован автоматически через Hilt
        }
    }
}