package com.example.shoppingcart.domain.usecase

import com.example.shoppingcart.data.CartRepository
import com.example.shoppingcart.data.CartItem

class UpdateCartItemUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(item: CartItem) {
        cartRepository.updateCartItem(item)
    }
}