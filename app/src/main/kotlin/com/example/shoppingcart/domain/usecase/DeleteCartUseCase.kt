package com.example.shoppingcart.domain.usecase

import com.example.shoppingcart.data.CartRepository

/**
 * UseCase для удаления всех элементов из корзины.
 *
 * @property cartRepository Репозиторий корзины, предоставляющий методы взаимодействия с данными корзины.
 */
class DeleteCartUseCase(private val cartRepository: CartRepository) {

    /**
     * Выполняет удаление всех товаров из корзины.
     *
     * Используется как операторный метод, что позволяет вызывать его через `invoke()`.
     * Например: `deleteCartUseCase()`.
     */
    suspend operator fun invoke() {
        cartRepository.deleteCart()
    }
}