package com.example.a35b_crud.model

data class CartItem(
    val productName: String,
    val price: Double,
    val imageUrl: Int, // Change this to String if using URLs from Firebase
    val quantity: Int
)
