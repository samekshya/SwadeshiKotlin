package com.example.a35b_crud.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a35b_crud.R
import com.example.a35b_crud.adapter.CartAdapter
import com.example.a35b_crud.model.CartItem
import com.example.a35b_crud.ui.activity.CheckoutActivity

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private var cartList: ArrayList<CartItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerCart)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample Cart Data for Testing (Replace with real data from database or ViewModel)
        cartList.add(CartItem("Handmade Bag", 1500.0, "https://example.com/image1.jpg", 1))
        cartList.add(CartItem("Pashmina Scarf", 2500.0, "https://example.com/image2.jpg", 2))

        // Initialize Adapter
        cartAdapter = CartAdapter(cartList) { item, position ->
            removeCartItem(position)
        }
        recyclerView.adapter = cartAdapter

        // Checkout Button Click Listener
        val btnCheckout = view.findViewById<Button>(R.id.btnCheckout)
        btnCheckout.setOnClickListener {
            if (cartList.isEmpty()) {
                Toast.makeText(requireContext(), "Cart is empty!", Toast.LENGTH_SHORT).show()
            } else {
                // Open CheckoutActivity and Pass Cart Items
                val intent = Intent(requireContext(), CheckoutActivity::class.java)
                intent.putParcelableArrayListExtra("cartItems", cartList)
                startActivity(intent)
            }
        }

        return view
    }

    // Remove item from cart
    private fun removeCartItem(position: Int) {
        cartList.removeAt(position)
        cartAdapter.notifyItemRemoved(position)
        Toast.makeText(requireContext(), "Item removed from cart", Toast.LENGTH_SHORT).show()
    }
}
