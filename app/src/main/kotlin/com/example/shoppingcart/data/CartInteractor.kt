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

/**
 * Класс CartItem описавает объект товара в корзине.
 *
 * @property id Уникальный идентификатор товара;
 * @property name Название товара;
 * @property count Количество товара. Должно находиться в диапазоне от 1 до 99 включительно;
 * @property price Цена товара. Цена должна быть положительной и больше нуля;
 * @property currency Валюта, в которой указана цена товара;
 * @property imageUrl URL изображения товара;
 *
 * @constructor Создает объект CartItem и дополнительную выполняет валидацию данных.
 * @throws IllegalArgumentException Если цена отрицательная или равна нулю, или количество товара выходит за пределы диапазона 1..99.
 */
data class CartItem(
    val id: String,
    val name: String,
    var count: Int,
    var price: Int,
    val currency: String,
    val imageUrl: String,
){
    init {
        require(price > 0) { "Цена не должна быть отрицательной" }
        require(count in 1..99) { "Количество не должно быть отрицательным и находиться в диапазоне 1..99" }
    }
}