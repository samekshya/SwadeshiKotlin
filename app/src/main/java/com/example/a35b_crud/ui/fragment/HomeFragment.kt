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
import com.example.a35b_crud.model.ProductModel

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var productList: ArrayList<ProductModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerProducts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ✅ FIX: Convert Int values to Double (1500 → 1500.0)
        productList.add(ProductModel("", "Handmade Bag", "Beautiful handmade bag", 1500.0, ""))
        productList.add(ProductModel("", "Pashmina Scarf", "Luxurious scarf from Nepal", 2500.0, ""))
        productList.add(ProductModel("", "Wooden Carving", "Handcrafted wooden art", 5000.0, ""))

        productAdapter = ProductAdapter(requireContext(), productList)
        recyclerView.adapter = productAdapter

        return view
    }
}
