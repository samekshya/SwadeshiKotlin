package com.example.a35b_crud.ui.fragment

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

        // Sample Cart Data for Testing (Replace this with real data later)
        cartList.add(CartItem("Handmade Bag", 1500.0, R.drawable.handmade_bag, 1))
        cartList.add(CartItem("Pashmina Scarf", 2500.0, R.drawable.pashmina_poncho, 2))

        // Initialize Adapter
        cartAdapter = CartAdapter(cartList) { item, position ->
            removeCartItem(position)
        }
        recyclerView.adapter = cartAdapter

        // Checkout Button Click Listener
        val btnCheckout = view.findViewById<Button>(R.id.btnCheckout)
        btnCheckout.setOnClickListener {
            Toast.makeText(requireContext(), "Proceeding to checkout...", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to CheckoutActivity
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
