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


@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val updateCartItemUseCase: UpdateCartItemUseCase,
    private val deleteCartUseCase: DeleteCartUseCase
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice

    init {
        loadCartItems()
    }

    fun loadCartItems() {
        viewModelScope.launch {
            val items = getCartItemsUseCase()
            _cartItems.value = items
            calculateTotalPrice(items)
        }
    }

    fun updateCartItem(item: CartItem) {
        viewModelScope.launch {
            updateCartItemUseCase(item)
            loadCartItems()
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            deleteCartUseCase()
            _cartItems.value = emptyList()
            _totalPrice.value = 0
        }
    }

    private fun calculateTotalPrice(items: List<CartItem>) {
        _totalPrice.value = items.sumOf { it.price * it.count }
    }
}