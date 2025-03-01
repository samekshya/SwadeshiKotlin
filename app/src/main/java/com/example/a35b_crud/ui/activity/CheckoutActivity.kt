package com.example.a35b_crud.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.a35b_crud.R

class CheckoutActivity : AppCompatActivity() {

    private lateinit var editAddress: EditText
    private lateinit var radioPaymentMethod: RadioGroup
    private lateinit var btnPlaceOrder: Button
    private lateinit var txtTotalPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)


        editAddress = findViewById(R.id.editAddress)
        radioPaymentMethod = findViewById(R.id.radioPaymentMethod)
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder)
        txtTotalPrice = findViewById(R.id.txtTotalPrice)

        // Get the total amount from intent
        val totalAmount = intent.getDoubleExtra("totalAmount", 0.0)
        txtTotalPrice.text = "Total: Rs. $totalAmount"

        // Debugging log
        Log.d("CheckoutActivity", "Received total: Rs. $totalAmount")

        btnPlaceOrder.setOnClickListener {
            placeOrder()
        }
    }

    private fun placeOrder() {
        val address = editAddress.text.toString().trim()
        val selectedPaymentId = radioPaymentMethod.checkedRadioButtonId

        // Check if a payment method is selected
        if (selectedPaymentId == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedPaymentMethod = if (selectedPaymentId == R.id.radioCashOnDelivery) {
            "Cash on Delivery"
        } else {
            "Online Payment"
        }

        // Validate Address
        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter a delivery address", Toast.LENGTH_SHORT).show()
            return
        }

        // Proceed with order placement (For now, we just show a success message)
        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_LONG).show()

        // TODO: Save order details to database (Firebase or local DB)

        // Navigate to Order Confirmation Page
        val intent = Intent(this, OrderConfirmationActivity::class.java)
        intent.putExtra("address", address)
        intent.putExtra("paymentMethod", selectedPaymentMethod)
        startActivity(intent)
        finish() // Close CheckoutActivity
    }
}
