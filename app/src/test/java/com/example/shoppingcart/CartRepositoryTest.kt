package com.example.shoppingcart

import com.example.shoppingcart.data.CartItem
import com.example.shoppingcart.data.CartRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

/**
 * Тесты на базовые операции с корзиной
 */
class CartRepositoryTest {

    private lateinit var cartRepository: CartRepository

    @Before
    fun setup() {
        cartRepository = CartRepository()
    }

    // Проверяем, что начально в корзине 4 элемента
    @Test
    fun testGetCartItems() = runBlocking {
        val items = cartRepository.getCartItems()
        assertEquals(4, items.size)
    }

    // Проверяем, что корзина пустая после удаления
    @Test
    fun testDeleteCart() = runBlocking {
        cartRepository.deleteCart()
        val items = cartRepository.getCartItems()
        assertEquals(0, items.size)
    }

    // Проверяем, что количество товара обновилось
    @Test
    fun testUpdateCartItem() = runBlocking {
        val itemToUpdate = CartItem("1", "Суп Том Ям с морепродуктами", 50, 100, "₽", "")
        cartRepository.updateCartItem(itemToUpdate)
        val items = cartRepository.getCartItems()
        assertEquals(50, items.find { it.id == "1" }?.count)
    }

    // Проверяем, что количество товара обновилось
    @Test
    fun testUpdateCartItem_multipleItems() = runBlocking {
        val itemsToUpdate = listOf(
            CartItem("1", "Суп Том Ям с морепродуктами", 50, 100, "₽", ""),
            CartItem("2", "Поке с индейкой и чуккой", 10, 200, "₽", "")
        )
        cartRepository.updateCartItem(itemsToUpdate)
        val items = cartRepository.getCartItems()
        assertEquals(50, items.find { it.id == "1" }?.count)
        assertEquals(10, items.find { it.id == "2" }?.count)
    }

    // Проверяем, что количество товара обновилось на 1
    @Test
    fun testUpdateCartItem_quantityToZero() = runBlocking {
        val itemToUpdate = CartItem("1", "Суп Том Ям с морепродуктами", 1, 100, "₽", "")
        cartRepository.updateCartItem(itemToUpdate)
        val items = cartRepository.getCartItems()
        assertEquals(1, items.find { it.id == "1" }?.count)
    }

    // Проверяем, что товар не был добавлен в корзину
    @Test
    fun testUpdateCartItem_nonExistingItem() = runBlocking {
        val itemToUpdate = CartItem("999", "Несуществующий товар", 50, 100, "₽", "")
        cartRepository.updateCartItem(itemToUpdate)
        val items = cartRepository.getCartItems()
        assertNull(items.find { it.id == "999" })
    }

    // Проверяем, что корзина очищается после удаления
    @Test
    fun testDeleteCart_afterItemUpdate() = runBlocking {
        val itemToUpdate = CartItem("1", "Суп Том Ям с морепродуктами", 50, 100, "₽", "")
        cartRepository.updateCartItem(itemToUpdate)
        cartRepository.deleteCart()
        val items = cartRepository.getCartItems()
        assertEquals(0, items.size)
    }

    // Проверяем, что товар с отрицательной ценой выбрасывает исключение
    @Test(expected = IllegalArgumentException::class)
    fun testNegativePriceItem() = runBlocking {
        val itemToUpdate = CartItem("1", "Товар с отрицательной ценой", 1, -100, "₽", "")
        cartRepository.updateCartItem(itemToUpdate)
    }

    // Проверяем, что при установке более 99 товаров выбрасывается исключение
    @Test(expected = IllegalArgumentException::class)
    fun testExtremeItemQuantity() = runBlocking {
        val itemWithExtremeQuantity =
            CartItem("1", "Товар с экстремальным количеством", 100, 100, "₽", "")
        cartRepository.updateCartItem(itemWithExtremeQuantity)
    }

    // Проверяем, что при установке 0 товаров выбрасывается исключение
    @Test(expected = IllegalArgumentException::class)
    fun testZeroItemQuantity() = runBlocking {
        val itemWithExtremeQuantity = CartItem("1", "Товар с 0v количеством", 0, 100, "₽", "")
        cartRepository.updateCartItem(itemWithExtremeQuantity)
    }
}