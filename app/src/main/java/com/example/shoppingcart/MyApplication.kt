package com.example.shoppingcart

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Класс приложения, используемый для инициализации Dagger Hilt.
 *
 * Аннотация @HiltAndroidApp автоматически генерирует базовый класс для инициализации
 * зависимостей Hilt. Это позволяет использовать инъекцию зависимостей в любом месте приложения.
 */
@HiltAndroidApp
class MyApplication : Application()