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
    private val cartList: ArrayList<CartItem>,
    private val onRemoveClick: (CartItem, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.cartItemImage)
        val productName: TextView = itemView.findViewById(R.id.cartItemName)
        val productPrice: TextView = itemView.findViewById(R.id.cartItemPrice)
        val productQuantity: TextView = itemView.findViewById(R.id.cartItemQuantity)
        val removeButton: Button = itemView.findViewById(R.id.btnRemoveItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_layout, parent, false)
        return CartViewHolder(itemView)
    }

    override fun getItemCount(): Int = cartList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartList[position]

        holder.productName.text = item.productName
        holder.productPrice.text = "Rs. ${item.price}"
        holder.productQuantity.text = "Qty: ${item.quantity}"

        // Load product image
        Picasso.get().load(item.imageUrl).into(holder.productImage)

        // Handle remove button click
        holder.removeButton.setOnClickListener {
            onRemoveClick(item, position)
        }
    }
}
