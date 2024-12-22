package com.example.shoppingcart.domain.usecase

import com.example.shoppingcart.data.CartRepository
import com.example.shoppingcart.data.CartItem

/**
 * UseCase для обновления данных одного элемента в корзине.
 *
 * @property cartRepository Репозиторий корзины, предоставляющий методы взаимодействия с данными корзины.
 */
class UpdateCartItemUseCase(private val cartRepository: CartRepository) {

    /**
     * Выполняет обновление данных указанного элемента корзины.
     *
     * Используется как операторный метод, что позволяет вызывать его через `invoke()`.
     * Например: `updateCartItemUseCase(item)`.
     *
     * @param item Объект [CartItem], содержащий обновленные данные элемента корзины.
     * @throws IllegalArgumentException Если данные объекта [CartItem] недействительны.
     * @throws Exception Если возникла ошибка при обновлении данных.
     */
    suspend operator fun invoke(item: CartItem) {
        cartRepository.updateCartItem(item)
    }
}