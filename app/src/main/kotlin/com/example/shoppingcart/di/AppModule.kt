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

/**
 * Модуль Dagger Hilt для предоставления зависимостей, связанных с функциональностью корзины.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Предоставляет экземпляр [CartRepository] как зависимость.
     *
     * @return Новый экземпляр [CartRepository].
     */
    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository {
        return CartRepository()
    }

    /**
     * Предоставляет экземпляр [GetCartItemsUseCase].
     *
     * @param cartRepository Репозиторий корзины.
     * @return Экземпляр [GetCartItemsUseCase].
     */
    @Provides
    fun provideGetCartItemsUseCase(cartRepository: CartRepository): GetCartItemsUseCase {
        return GetCartItemsUseCase(cartRepository)
    }

    /**
     * Предоставляет экземпляр [UpdateCartItemUseCase].
     *
     * @param cartRepository Репозиторий корзины.
     * @return Экземпляр [UpdateCartItemUseCase].
     */
    @Provides
    fun provideUpdateCartItemUseCase(cartRepository: CartRepository): UpdateCartItemUseCase {
        return UpdateCartItemUseCase(cartRepository)
    }

    /**
     * Предоставляет экземпляр [DeleteCartUseCase].
     *
     * @param cartRepository Репозиторий корзины.
     * @return Экземпляр [DeleteCartUseCase].
     */
    @Provides
    fun provideDeleteCartUseCase(cartRepository: CartRepository): DeleteCartUseCase {
        return DeleteCartUseCase(cartRepository)
    }
}