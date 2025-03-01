package com.example.a35b_crud.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a35b_crud.R
import com.example.a35b_crud.model.CartItem
import com.example.a35b_crud.model.ProductModel
import com.example.a35b_crud.utils.CartManager
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ProductAdapter(
    private val context: Context,
    private var data: ArrayList<ProductModel>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.getImage)
        val loading: ProgressBar = itemView.findViewById(R.id.progressBar2)
        val pName: TextView = itemView.findViewById(R.id.displayName)
        val pPrice: TextView = itemView.findViewById(R.id.displayPrice)
        val pDesc: TextView = itemView.findViewById(R.id.displayDesc)
        val btnAddToCart: Button = itemView.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView: View = LayoutInflater.from(context)
            .inflate(R.layout.sample_products, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = data[position]

        holder.pName.text = product.productName
        holder.pPrice.text = "Rs. ${product.price}"
        holder.pDesc.text = product.productDesc

        // Load Image (Check if it's a drawable resource or URL)
        if (product.imageUrl != 0) {
            // ✅ Load image from drawable folder
            holder.imageView.setImageResource(product.imageUrl)
            holder.loading.visibility = View.GONE
        } else {
            // ✅ Load image from URL (if applicable)
            Picasso.get()
                .load(R.drawable.default_image) // You can change this logic if needed
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(holder.imageView, object : Callback {
                    override fun onSuccess() {
                        holder.loading.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        holder.loading.visibility = View.GONE
                    }
                })
        }

        // Add to Cart Button
        holder.btnAddToCart.setOnClickListener {
            val cartItem = CartItem(product.productName, product.price, product.imageUrl, 1)
            CartManager.addToCart(cartItem)
            Toast.makeText(context, "${product.productName} added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateData(products: List<ProductModel>) {
        data.clear()
        data.addAll(products)
        notifyDataSetChanged()
    }

    fun getProductId(position: Int): String {
        return data[position].productId
    }
}
