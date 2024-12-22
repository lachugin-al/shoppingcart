package com.example.shoppingcart.domain.usecase

import com.example.shoppingcart.data.CartRepository

class DeleteCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke() {
        cartRepository.deleteCart()
    }
}