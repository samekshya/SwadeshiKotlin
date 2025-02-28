package com.example.a35b_crud.utils

import com.example.a35b_crud.model.CartItem

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addToCart(item: CartItem) {
        val validImageUrl = item.imageUrl.takeIf { !it.isNullOrEmpty() } ?: "https://your-default-image-url.com/default.png"

        val existingItem = cartItems.find { it.name == item.name }
        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            cartItems[cartItems.indexOf(existingItem)] = updatedItem
        } else {
            cartItems.add(item.copy(imageUrl = validImageUrl))
        }
    }

    fun removeFromCart(item: CartItem) {
        val index = cartItems.indexOfFirst { it.name == item.name }
        if (index != -1) {
            cartItems.removeAt(index)
        }
    }

    fun getCartItems(): List<CartItem> {
        return cartItems.toList() // Prevent modification outside this class
    }

    fun clearCart() {
        cartItems.clear()
    }
}
