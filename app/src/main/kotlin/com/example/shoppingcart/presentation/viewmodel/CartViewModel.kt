package com.example.shoppingcart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcart.data.CartItem
import com.example.shoppingcart.domain.usecase.DeleteCartUseCase
import com.example.shoppingcart.domain.usecase.GetCartItemsUseCase
import com.example.shoppingcart.domain.usecase.UpdateCartItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для управления состоянием корзины.
 *
 * @property getCartItemsUseCase UseCase для получения списка товаров.
 * @property updateCartItemUseCase UseCase для обновления элемента корзины.
 * @property deleteCartUseCase UseCase для очистки корзины.
 */
@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val updateCartItemUseCase: UpdateCartItemUseCase,
    private val deleteCartUseCase: DeleteCartUseCase
) : ViewModel() {

    // Состояние списка товаров в корзине.
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    // Состояние общей стоимости товаров в корзине.
    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice

    init {
        // Загрузка товаров при инициализации ViewModel.
        loadCartItems()
    }

    /**
     * Загружает список товаров в корзине и обновляет общее состояние.
     */
    fun loadCartItems() {
        viewModelScope.launch {
            try {
                val items = getCartItemsUseCase()
                _cartItems.value = items
                calculateTotalPrice(items)
            } catch (e: Exception) {
                println("Ошибка при загрузке товаров: ${e.message}")
            }
        }
    }

    /**
     * Обновляет данные конкретного товара в корзине.
     *
     * @param item Объект [CartItem] с обновленными данными.
     */
    fun updateCartItem(item: CartItem) {
        viewModelScope.launch {
            try {
                updateCartItemUseCase(item)
                loadCartItems()
            } catch (e: Exception) {
                println("Ошибка при обновлении товара: ${e.message}")
            }
        }
    }

    /**
     * Очищает корзину и сбрасывает состояние.
     */
    fun clearCart() {
        viewModelScope.launch {
            try {
                deleteCartUseCase()
                _cartItems.value = emptyList()
                _totalPrice.value = 0
            } catch (e: Exception) {
                println("Ошибка при очистке корзины: ${e.message}")
            }
        }
    }

    /**
     * Вычисляет общую стоимость товаров в корзине.
     *
     * @param items Список объектов [CartItem].
     */
    private fun calculateTotalPrice(items: List<CartItem>) {
        _totalPrice.value = items.sumOf { it.price * it.count }
    }
}