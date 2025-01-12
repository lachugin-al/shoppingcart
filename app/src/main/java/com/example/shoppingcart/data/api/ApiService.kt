package com.example.shoppingcart.data.api

import retrofit2.http.POST

/**
 * Интерфейс для взаимодействия с API.
 */
interface ApiService {
    @POST("/api/send-test-order")
    suspend fun sendTestOrder()
}
