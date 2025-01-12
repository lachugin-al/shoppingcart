package com.example.shoppingcart.domain.usecase

import com.example.shoppingcart.data.api.RetrofitInstance
import javax.inject.Inject

/**
 * UseCase для отправки тестового заказа на сервер.
 */
class SendTestOrderUseCase @Inject constructor() {

    /**
     * Отправляет POST-запрос на сервер.
     */
    suspend operator fun invoke() {
        try {
            RetrofitInstance.api.sendTestOrder()
            println("Заказ успешно отправлен.")
        } catch (e: Exception) {
            println("Ошибка при отправке заказа: ${e.message}")
            throw e
        }
    }
}
