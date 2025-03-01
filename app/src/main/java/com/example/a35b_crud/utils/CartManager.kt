package com.example.a35b_crud.utils

import com.example.a35b_crud.model.CartItem

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addToCart(item: CartItem) {
        val existingItem = cartItems.find { it.name == item.name }
        if (existingItem != null) {
            val updatedQuantity = existingItem.quantity + 1
            val updatedItem = existingItem.copy(
                quantity = updatedQuantity,
                totalPrice = existingItem.price * updatedQuantity // ✅ Ensure totalPrice updates
            )
            cartItems[cartItems.indexOf(existingItem)] = updatedItem
        } else {
            cartItems.add(
                item.copy(quantity = 1, totalPrice = item.price) // ✅ Set initial totalPrice correctly
            )
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
