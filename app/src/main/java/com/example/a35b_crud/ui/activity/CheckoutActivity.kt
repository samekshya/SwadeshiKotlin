package com.example.a35b_crud.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a35b_crud.R

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val btnPlaceOrder = findViewById<Button>(R.id.btnPlaceOrder)

        btnPlaceOrder.setOnClickListener {
            // Open the Order Confirmation Page
            startActivity(Intent(this, OrderConfirmationActivity::class.java))
            finish() // Close checkout page
        }
    }
}
