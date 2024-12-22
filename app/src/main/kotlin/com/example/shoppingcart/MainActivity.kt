package com.example.shoppingcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.shoppingcart.data.CartRepository
import com.example.shoppingcart.ui.CartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cartRepository = CartRepository()

        setContent {
            CartScreen(cartInteractor = cartRepository)
        }
    }
}
