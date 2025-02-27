package com.example.a35b_crud.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a35b_crud.R
import com.example.a35b_crud.adapter.ProductAdapter
import com.example.a35b_crud.model.Product

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var productList: ArrayList<Product> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerProducts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample Data for Testing
        productList.add(Product("Handmade Bag", 1500.0, R.drawable.handmade_bag))
        productList.add(Product("Pashmina Scarf", 2500.0, R.drawable.pashmina_scarf))
        productList.add(Product("Wooden Carving", 5000.0, R.drawable.wooden_carving))

        // Initialize Adapter
        productAdapter = ProductAdapter(productList)
        recyclerView.adapter = productAdapter

        return view
    }
}
