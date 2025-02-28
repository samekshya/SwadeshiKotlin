package com.example.a35b_crud.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a35b_crud.R
import com.example.a35b_crud.adapter.CartAdapter
import com.example.a35b_crud.model.CartItem
import com.example.a35b_crud.ui.activity.CheckoutActivity
import com.example.a35b_crud.utils.CartManager

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var btnCheckout: Button
    private lateinit var txtTotalPrice: TextView
    private var cartList: ArrayList<CartItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.recyclerCart)
        btnCheckout = view.findViewById(R.id.btnCheckout)
        txtTotalPrice = view.findViewById(R.id.txtTotalPrice)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Load cart items
        cartList = ArrayList(CartManager.getCartItems())

        if (cartList.isEmpty()) {
            Toast.makeText(requireContext(), "Cart is empty!", Toast.LENGTH_SHORT).show()
        }

        // Initialize Adapter
        cartAdapter = CartAdapter(cartList) { item, position ->
            removeCartItem(position)
        }
        recyclerView.adapter = cartAdapter

        updateTotalPrice() // Update total price initially

        btnCheckout.setOnClickListener {
            if (cartList.isEmpty()) {
                Toast.makeText(requireContext(), "Cart is empty!", Toast.LENGTH_SHORT).show()
            } else {
                // Navigate to CheckoutActivity
                val intent = Intent(requireContext(), CheckoutActivity::class.java)
                startActivity(intent)
            }
        }

        return view
    }

    private fun removeCartItem(position: Int) {
        if (position >= 0 && position < cartList.size) {
            val item = cartList[position]
            CartManager.removeFromCart(item)
            cartList.removeAt(position)
            cartAdapter.notifyDataSetChanged() // Update entire list to avoid index issues
            updateTotalPrice() // Update total price after removal
            Toast.makeText(requireContext(), "Item removed from cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateTotalPrice() {
        val total = cartList.sumOf { it.price * it.quantity }
        txtTotalPrice.text = "Total: Rs. $total"
    }
}
