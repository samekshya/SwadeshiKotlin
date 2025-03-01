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

        // ✅ Adding images from drawable (Replace with actual images in your drawable folder)
        productList.add(ProductModel("", "Handmade Bag", "Beautiful handmade bag", 1500.0, R.drawable.handmade_bag))
        productList.add(ProductModel("", "Pashmina Poncho", "Luxurious scarf from Nepal", 2500.0, R.drawable.pashmina_poncho))
        productList.add(ProductModel("", "Women's Pink Hoodie", "Luxurious Pashmina Pink hoodie", 1500.0, R.drawable.pink_hoodie))
        productList.add(ProductModel("", "Women's Grey Highneck", "Luxurious Pashmina Grey hoodie", 1500.0, R.drawable.grey_highneck))
        productList.add(ProductModel("", "Men's Brown Sweater", "Luxurious Pashmina Brown hoodie", 1500.0, R.drawable.brown_sweater))
        productList.add(ProductModel("", "Pustakari", "Authentic Nepali Pustakari", 750.0, R.drawable.pustakari))
        productList.add(ProductModel("", "Cookies", "Authentic Cookies made from Gundpak", 250.0, R.drawable.cookies))
        productList.add(ProductModel("", "Coffee", "Medium Roasted Fine Grind Coffee", 1000.0, R.drawable.coffee))
        productList.add(ProductModel("", "Tennis Bracelet", "Tennis Bracelet Made from Pure Silver And Zircon", 2000.0, R.drawable.tennis_bracelet))
        productList.add(ProductModel("", "Spicy Titaura", "Spicy Lapsi Titaura", 300.0, R.drawable.spicy_titaura))

        productAdapter = ProductAdapter(requireContext(), productList)
        recyclerView.adapter = productAdapter

        return view
    }
}
