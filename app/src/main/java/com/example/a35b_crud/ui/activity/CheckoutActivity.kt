package com.example.a35b_crud.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a35b_crud.R
import com.example.a35b_crud.adapter.CartAdapter
import com.example.a35b_crud.model.CartItem

class CheckoutActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private var cartList: ArrayList<CartItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Receive Cart Items from Intent
        cartList = intent.getParcelableArrayListExtra("cartItems") ?: arrayListOf()

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerCheckout)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cartAdapter = CartAdapter(cartList) { _, _ -> }
        recyclerView.adapter = cartAdapter

        // Calculate Total Price
        val totalPrice = cartList.sumOf { it.price * it.quantity }
        findViewById<TextView>(R.id.txtTotalPrice).text = "Total: Rs. $totalPrice"

        // TODO: Add logic to proceed with payment or place order
    }
}
