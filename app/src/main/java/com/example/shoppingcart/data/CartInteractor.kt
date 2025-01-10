package com.example.shoppingcart.data

/**
 * Интерфейс для взаимодействия с элементами корзины.
 * Предоставляет методы для получения, обновления и очистки содержимого корзины.
 */
interface CartInteractor {

    /**
     * Получает список всех элементов в корзине.
     *
     * @return Список объектов [CartItem], представляющих товары в корзине.
     *
     * @throws Exception Если возникла ошибка при получении данных.
     */
    suspend fun getCartItems(): List<CartItem>

    /**
     * Очищает корзину, удаляя все её содержимое.
     *
     * @throws Exception Если возникла ошибка при удалении данных.
     */
    suspend fun deleteCart()

    /**
     * Обновляет информацию об указанном элементе корзины.
     *
     * @param item Объект [CartItem], содержащий обновленные данные.
     *
     * @throws IllegalArgumentException Если переданный элемент недействителен.
     * @throws Exception Если возникла ошибка при обновлении элемента.
     */
    suspend fun updateCartItem(item: CartItem)

    /**
     * Обновляет информацию о нескольких элементах корзины.
     *
     * @param items Список объектов [CartItem], содержащих обновленные данные.
     *
     * @throws IllegalArgumentException Если список пуст или содержит недействительные элементы.
     * @throws Exception Если возникла ошибка при обновлении элементов.
     */
    suspend fun updateCartItem(items: List<CartItem>)
}