package com.example.a35b_crud.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a35b_crud.R

class OrderConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        val txtOrderMessage = findViewById<TextView>(R.id.txtOrderMessage)
        txtOrderMessage.text = "Thank you! Your order has been placed successfully."
    }
}
