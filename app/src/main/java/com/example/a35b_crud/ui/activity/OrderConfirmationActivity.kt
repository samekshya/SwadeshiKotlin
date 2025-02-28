package com.example.a35b_crud.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a35b_crud.R

class OrderConfirmationActivity : AppCompatActivity() {

    private lateinit var txtOrderSummary: TextView
    private lateinit var txtDeliveryAddress: TextView
    private lateinit var txtPaymentMethod: TextView
    private lateinit var btnBackToHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        // Initialize UI Elements
        txtOrderSummary = findViewById(R.id.txtOrderSummary)
        txtDeliveryAddress = findViewById(R.id.txtDeliveryAddress)
        txtPaymentMethod = findViewById(R.id.txtPaymentMethod)
        btnBackToHome = findViewById(R.id.btnBackToHome)

        // Get Order Details from Intent
        val address = intent.getStringExtra("address") ?: "No Address Provided"
        val paymentMethod = intent.getStringExtra("paymentMethod") ?: "Not Specified"

        // Set Values in UI
        txtOrderSummary.text = "Your order has been placed successfully!"
        txtDeliveryAddress.text = "Delivery Address: $address"
        txtPaymentMethod.text = "Payment Method: $paymentMethod"

        // Back to Home Button
        btnBackToHome.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java) // Change this to your main home activity
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
