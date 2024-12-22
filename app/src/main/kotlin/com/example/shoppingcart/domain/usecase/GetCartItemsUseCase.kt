package com.example.shoppingcart.domain.usecase

import com.example.shoppingcart.data.CartRepository
import com.example.shoppingcart.data.CartItem

class GetCartItemsUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(): List<CartItem> {
        return cartRepository.getCartItems()
    }
}