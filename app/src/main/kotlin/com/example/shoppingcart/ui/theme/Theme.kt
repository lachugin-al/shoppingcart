package com.example.shoppingcart.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Определение цветовой схемы для тёмной темы
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Определение цветовой схемы для светлой темы
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

/**
 * Компонент темы для приложения ShoppingCart.
 *
 * @param darkTheme Указывает, должна ли использоваться тёмная тема. По умолчанию определяется системными настройками.
 * @param dynamicColor Указывает, должны ли использоваться динамические цвета. Доступно только на Android 12+.
 * @param content Содержимое, которое будет использовано внутри темы.
 */
@Composable
fun ShoppingcartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Выбор цветовой схемы в зависимости от настроек
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Применение темы Material3
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}