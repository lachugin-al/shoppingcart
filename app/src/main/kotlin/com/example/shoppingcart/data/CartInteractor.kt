package com.example.shoppingcart.data


/**
 * Интерфейс взаимодействия с элементами корзины
 */
interface CartInteractor {

    /**
     * Возвращает список всех элементов в корзине
     */
    suspend fun getCartItems(): List<CartItem>

    /**
     * Удаляет корзину
     */
    suspend fun deleteCart()

    /**
     * Обновляет элемент корзины
     */
    suspend fun updateCartItem(item: CartItem)

    /**
     * Обновляет элементы корзины
     */
    suspend fun updateCartItem(items: List<CartItem>)
}