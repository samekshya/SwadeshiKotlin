package com.example.a35b_crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a35b_crud.R
import com.example.a35b_crud.model.CartItem
import com.squareup.picasso.Picasso

class CartAdapter(
    private var cartItems: MutableList<CartItem>,
    private val onRemoveClick: (CartItem, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.cartImage)
        val name: TextView = itemView.findViewById(R.id.cartName)
        val price: TextView = itemView.findViewById(R.id.cartPrice)
        val quantity: TextView = itemView.findViewById(R.id.cartQuantity)
        val btnRemove: Button = itemView.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]

        holder.name.text = item.name
        holder.price.text = "Rs. ${item.price}"
        holder.quantity.text = "Qty: ${item.quantity}"

        // ✅ Fix: Use imageUrl as a drawable resource ID
        holder.imageView.setImageResource(item.imageUrl)

        // Remove button
        holder.btnRemove.setOnClickListener {
            onRemoveClick(item, position)
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun updateList(newList: MutableList<CartItem>) {
        cartItems = newList
        notifyDataSetChanged()
    }
}
