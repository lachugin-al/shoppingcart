package com.example.shoppingcart.di

import com.example.shoppingcart.data.CartRepository
import com.example.shoppingcart.domain.usecase.DeleteCartUseCase
import com.example.shoppingcart.domain.usecase.GetCartItemsUseCase
import com.example.shoppingcart.domain.usecase.UpdateCartItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository {
        return CartRepository()
    }

    @Provides
    fun provideGetCartItemsUseCase(cartRepository: CartRepository): GetCartItemsUseCase {
        return GetCartItemsUseCase(cartRepository)
    }

    @Provides
    fun provideUpdateCartItemUseCase(cartRepository: CartRepository): UpdateCartItemUseCase {
        return UpdateCartItemUseCase(cartRepository)
    }

    @Provides
    fun provideDeleteCartUseCase(cartRepository: CartRepository): DeleteCartUseCase {
        return DeleteCartUseCase(cartRepository)
    }
}