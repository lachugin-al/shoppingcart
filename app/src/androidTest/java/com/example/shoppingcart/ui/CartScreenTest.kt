package com.example.shoppingcart.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.shoppingcart.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * UI тесты для экрана корзины.
 */
class CartScreenTest {

    // Правило для тестирования Compose UI
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    // Инициализация перед каждым тестом
    @Before
    fun setUp() {
        // Ожидаем загрузки UI
        composeTestRule.waitForIdle()
    }

    /**
     * Тест проверяет, что экран корзины отображает список товаров с корректными данными.
     */
    @Test
    fun cartScreen_displaysCartItemsWithCorrectData() {
        // Ожидаем загрузки UI
        composeTestRule.waitForIdle()

        // Проверяем, что контейнер корзины отображается
        composeTestRule.onNode(hasContentDescription("cart_screen_container")).assertIsDisplayed()

        // Проверяем, что список товаров отображается
        composeTestRule.onNode(hasContentDescription("cart_items_list")).assertIsDisplayed()

        // Проверяем, что заголовок корзины отображается
        composeTestRule.onNode(hasContentDescription("cart_header")).assertIsDisplayed()

        // Проверяем, что кнопка оформления заказа отображается
        composeTestRule.onNode(hasContentDescription("checkout_button_container")).assertIsDisplayed()

        // Проверяем, что товары отображаются с корректными данными
        // Проверяем товар с ID "1" (начальное количество 98)
        composeTestRule.onNode(hasContentDescription("cart_item_row_1")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("cart_item_name_1")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("cart_item_price_1")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("counter_value_1")).assertTextEquals("98")

        // Проверяем товар с ID "4" (начальное количество 99)
        composeTestRule.onNode(hasContentDescription("cart_item_row_4")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("cart_item_name_4")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("cart_item_price_4")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("counter_value_4")).assertTextEquals("99")

        // Проверяем, что общая стоимость корзины отображается
        composeTestRule.onNode(hasContentDescription("checkout_button")).assertExists()
    }

    /**
     * Тест проверяет, что при очистке корзины отображается сообщение о пустой корзине
     * и скрываются элементы корзины.
     */
    @Test
    fun cartScreen_whenCartIsEmpty_displaysEmptyCartMessage() {
        // Ожидаем загрузки UI
        composeTestRule.waitForIdle()

        // Проверяем, что изначально корзина не пуста
        composeTestRule.onNode(hasContentDescription("cart_screen_container")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("empty_cart_container")).assertDoesNotExist()

        // Находим кнопку очистки корзины и кликаем по ней
        composeTestRule.onNode(hasContentDescription("Удалить корзину")).performClick()

        // Ожидаем обновления UI
        composeTestRule.waitForIdle()

        // Проверяем, что отображается сообщение о пустой корзине
        composeTestRule.onNode(hasContentDescription("empty_cart_container")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("empty_cart_message")).assertIsDisplayed()
        composeTestRule.onNodeWithText("Ваша корзина пуста").assertIsDisplayed()

        // Проверяем, что элементы корзины скрыты
        composeTestRule.onNode(hasContentDescription("cart_screen_container")).assertDoesNotExist()
        composeTestRule.onNode(hasContentDescription("cart_items_list")).assertDoesNotExist()
        composeTestRule.onNode(hasContentDescription("checkout_button_container")).assertDoesNotExist()
    }

    /**
     * Тест проверяет, что при отправке заказа отображается сообщение об успешной отправке
     * и скрываются элементы корзины.
     */
    @Test
    fun cartScreen_whenOrderIsSent_displaysSuccessMessage() {
        // Ожидаем загрузки UI
        composeTestRule.waitForIdle()

        // Проверяем, что изначально корзина не пуста и сообщение об успешной отправке не отображается
        composeTestRule.onNode(hasContentDescription("cart_screen_container")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("order_success_container")).assertDoesNotExist()

        // Находим кнопку оформления заказа и кликаем по ней
        composeTestRule.onNode(hasContentDescription("checkout_button")).performClick()

        // Ожидаем обновления UI
        composeTestRule.waitForIdle()

        // Проверяем, что отображается сообщение об успешной отправке заказа
        composeTestRule.onNode(hasContentDescription("order_success_container")).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription("order_success_message")).assertIsDisplayed()
        composeTestRule.onNodeWithText("Заказ успешно отправлен!").assertIsDisplayed()

        // Проверяем, что элементы корзины скрыты
        composeTestRule.onNode(hasContentDescription("cart_screen_container")).assertDoesNotExist()
        composeTestRule.onNode(hasContentDescription("cart_items_list")).assertDoesNotExist()
        composeTestRule.onNode(hasContentDescription("checkout_button_container")).assertDoesNotExist()
    }

    /**
     * Тест проверяет, что можно увеличить количество товара в корзине и значение счетчика обновляется.
     */
    @Test
    fun cartScreen_canIncreaseItemQuantity() {
        // Ожидаем загрузки UI
        composeTestRule.waitForIdle()

        // Используем товар с ID "3", у которого начальное количество 1
        // Проверяем начальное значение счетчика
        composeTestRule.onNode(hasContentDescription("counter_value_3")).assertTextEquals("1")

        // Находим кнопку увеличения количества товара и кликаем по ней
        composeTestRule.onNode(hasContentDescription("counter_plus_button_3")).performClick()

        // Ожидаем обновления UI
        composeTestRule.waitForIdle()

        // Проверяем, что значение счетчика увеличилось на 1
        composeTestRule.onNode(hasContentDescription("counter_value_3")).assertTextEquals("2")
    }

    /**
     * Тест проверяет, что можно уменьшить количество товара в корзине и значение счетчика обновляется.
     */
    @Test
    fun cartScreen_canDecreaseItemQuantity() {
        // Ожидаем загрузки UI
        composeTestRule.waitForIdle()

        // Используем товар с ID "2", у которого начальное количество 2
        // Проверяем начальное значение счетчика
        composeTestRule.onNode(hasContentDescription("counter_value_2")).assertTextEquals("2")

        // Находим кнопку уменьшения количества товара и кликаем по ней
        composeTestRule.onNode(hasContentDescription("counter_minus_button_2")).performClick()

        // Ожидаем обновления UI
        composeTestRule.waitForIdle()

        // Проверяем, что значение счетчика уменьшилось на 1
        composeTestRule.onNode(hasContentDescription("counter_value_2")).assertTextEquals("1")
    }

    /**
     * Тест проверяет, что нельзя уменьшить количество товара ниже минимального значения (1).
     */
    @Test
    fun cartScreen_cannotDecreaseItemQuantityBelowMinimum() {
        // Ожидаем загрузки UI
        composeTestRule.waitForIdle()

        // Используем товар с ID "3", у которого начальное количество 1
        // Проверяем начальное значение счетчика
        composeTestRule.onNode(hasContentDescription("counter_value_3")).assertTextEquals("1")

        // Находим кнопку уменьшения количества товара и кликаем по ней
        composeTestRule.onNode(hasContentDescription("counter_minus_button_3")).performClick()

        // Ожидаем обновления UI
        composeTestRule.waitForIdle()

        // Проверяем, что значение счетчика не изменилось и осталось равным 1
        composeTestRule.onNode(hasContentDescription("counter_value_3")).assertTextEquals("1")
    }

    /**
     * Тест проверяет, что при изменении количества товара обновляется общая стоимость.
     */
    @Test
    fun cartScreen_updatesItemTotalPriceWhenQuantityChanges() {
        // Ожидаем загрузки UI
        composeTestRule.waitForIdle()

        // Используем товар с ID "3", у которого начальное количество 1
        // Проверяем, что кнопка оформления заказа отображается
        composeTestRule.onNode(hasContentDescription("checkout_button")).assertExists()

        // Проверяем начальное значение счетчика
        composeTestRule.onNode(hasContentDescription("counter_value_3")).assertTextEquals("1")

        // Увеличиваем количество товара
        composeTestRule.onNode(hasContentDescription("counter_plus_button_3")).performClick()
        composeTestRule.waitForIdle()

        // Проверяем, что значение счетчика увеличилось
        composeTestRule.onNode(hasContentDescription("counter_value_3")).assertTextEquals("2")

        // Проверяем, что цена товара отображается
        composeTestRule.onNode(hasContentDescription("cart_item_price_3")).assertExists()

        // Проверяем, что кнопка оформления заказа все еще отображается
        // (общая стоимость должна была обновиться автоматически)
        composeTestRule.onNode(hasContentDescription("checkout_button")).assertExists()

        // Уменьшаем количество товара обратно
        composeTestRule.onNode(hasContentDescription("counter_minus_button_3")).performClick()
        composeTestRule.waitForIdle()

        // Проверяем, что значение счетчика уменьшилось
        composeTestRule.onNode(hasContentDescription("counter_value_3")).assertTextEquals("1")
    }

    /**
     * Тест проверяет максимальное значение счетчика (99).
     */
    @Test
    fun cartScreen_cannotIncreaseItemQuantityAboveMaximum() {
        // Ожидаем загрузки UI
        composeTestRule.waitForIdle()

        // Используем товар с ID "4", у которого начальное количество уже 99 (максимальное)
        // Проверяем начальное значение счетчика
        composeTestRule.onNode(hasContentDescription("counter_value_4")).assertTextEquals("99")

        // Пытаемся увеличить количество товара еще раз
        composeTestRule.onNode(hasContentDescription("counter_plus_button_4")).performClick()
        composeTestRule.waitForIdle()

        // Проверяем, что значение счетчика не изменилось и осталось равным 99
        composeTestRule.onNode(hasContentDescription("counter_value_4")).assertTextEquals("99")
    }
}