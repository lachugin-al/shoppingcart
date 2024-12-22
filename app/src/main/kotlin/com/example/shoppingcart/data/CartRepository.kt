package com.example.shoppingcart.data

/**
 * Репозиторий корзины, реализующий интерфейс взаимодействия с элементами [CartInteractor].
 */
class CartRepository : CartInteractor {

    // Временное хранилище элементов корзины.
    private val cartItems = mutableListOf<CartItem>(
        CartItem(
            "1",
            "Суп Том Ям с морепродуктами",
            99,
            100,
            "₽",
            "https://i.pinimg.com/736x/70/b0/d5/70b0d50c800039db6f1d58273efbf542.jpg"
        ),
        CartItem(
            "2",
            "Поке с индейкой и чуккой",
            2,
            100,
            "₽",
            "http://localhost:8080/poke_with_chiken.png"
        ),
        CartItem(
            "3",
            "Поке с тунцом, лососем, авокадо, чеддером",
            1,
            100,
            "₽",
            "http://localhost:8080/poke_with_chiken"
        ),
        CartItem(
            "4",
            "Блинчики с малиной и маскарпоне",
            1,
            100,
            "₽",
            "https://i.pinimg.com/736x/70/b0/d5/70b0d50c800039db6f1d58273efbf542.jpg"
        )
    )

    /**
     * Возвращает список всех элементов, находящихся в корзине.
     *
     * @return Список объектов [CartItem], представляющих товары в корзине.
     */
    override suspend fun getCartItems(): List<CartItem> {
        return cartItems
    }

    /**
     * Очищает корзину, удаляя все товары из списка.
     */
    override suspend fun deleteCart() {
        cartItems.clear()
    }

    /**
     * Обновляет количество товара в корзине на основе идентификатора товара.
     * Если товар с таким идентификатором найден в корзине, его количество обновляется до
     * значения, переданного в параметре [item].
     *
     * @param item Объект [CartItem], представляющий товар с обновленным количеством.
     */
    override suspend fun updateCartItem(item: CartItem) {
        validateCartItem(item)

        val existingItem = cartItems.find { it.id == item.id }
        if (existingItem == null) {
            println("Товар с id ${item.id} не найден в корзине")
            return
        }

        existingItem.apply {
            count = item.count
            price = item.price
        }
    }

    /**
     * Обновляет количество товаров в корзине на основе списка товаров.
     * Для каждого товара в списке обновляется его количество в корзине, если товар с таким
     * идентификатором найден.
     *
     * @param items Список объектов [CartItem], представляющих товары с обновленным количеством.
     */
    override suspend fun updateCartItem(items: List<CartItem>) {
        items.forEach {
            validateCartItem(it)
            updateCartItem(it)
        }
    }

    /**
     * Проверяет корректность данных объекта [CartItem].
     *
     * @param item Объект [CartItem] для проверки.
     * @throws IllegalArgumentException Если данные недействительны.
     */
    private fun validateCartItem(item: CartItem) {
        require(item.price > 0) { "Цена не должна быть отрицательной" }
        require(item.count in 1..99) { "Количество товаров должно быть в диапазоне 1..99" }
    }
}