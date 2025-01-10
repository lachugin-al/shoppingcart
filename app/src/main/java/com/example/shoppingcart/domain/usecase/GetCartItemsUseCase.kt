package com.example.shoppingcart.domain.usecase

import com.example.shoppingcart.data.CartRepository
import com.example.shoppingcart.data.CartItem

/**
 * UseCase для получения списка всех элементов корзины.
 *
 * @property cartRepository Репозиторий корзины, предоставляющий методы взаимодействия с данными корзины.
 */
class GetCartItemsUseCase(private val cartRepository: CartRepository) {

    /**
     * Выполняет запрос списка товаров в корзине.
     *
     * Используется как операторный метод, что позволяет вызывать его через `invoke()`.
     * Например: `getCartItemsUseCase()`.
     *
     * @return Список объектов [CartItem], представляющих товары в корзине.
     * @throws Exception Если произошла ошибка при получении данных.
     */
    suspend operator fun invoke(): List<CartItem> {
        return cartRepository.getCartItems()
    }
}