package com.example.a35b_crud.ui.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a35b_crud.R
import com.example.a35b_crud.databinding.ActivityAddProductBinding
import com.example.a35b_crud.model.ProductModel
import com.example.a35b_crud.repository.ProductRepistoryImpl
import com.example.a35b_crud.utils.ImageUtils
import com.example.a35b_crud.utils.LoadingUtils
import com.example.a35b_crud.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var loadingUtils: LoadingUtils
    private lateinit var imageUtils: ImageUtils

    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageUtils = ImageUtils(this)
        loadingUtils = LoadingUtils(this)

        val repo = ProductRepistoryImpl()
        productViewModel = ProductViewModel(repo)

        // Set up Image Picker
        imageUtils.registerActivity { url ->
            url?.let {
                imageUri = it
                Picasso.get().load(it).into(binding.imageBrowse)
            }
        }

        binding.imageBrowse.setOnClickListener {
            imageUtils.launchGallery(this)
        }

        // Add Product Button Click
        binding.btnAddProduct.setOnClickListener {
            Log.d("DEBUG", "Add Product button clicked!") // Debugging log
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            uploadImage()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun uploadImage() {
        loadingUtils.show()

        if (imageUri == null) {
            Toast.makeText(this, "Please select an image first!", Toast.LENGTH_LONG).show()
            loadingUtils.dismiss()
            return
        }

        imageUri?.let { uri ->
            productViewModel.uploadImage(this, uri) { imageUrl ->
                Log.d("DEBUG", "Image uploaded: $imageUrl")
                if (imageUrl != null) {
                    addProduct(imageUrl)
                } else {
                    Log.e("Upload Error", "Failed to upload image to Cloudinary")
                    Toast.makeText(this, "Image upload failed", Toast.LENGTH_SHORT).show()
                    loadingUtils.dismiss()
                }
            }
        }
    }

    private fun addProduct(url: String) {
        val productName = binding.editProductName.text.toString().trim()
        val productDesc = binding.editProductDesc.text.toString().trim()
        val productPrice = binding.editProductprice.text.toString().toDoubleOrNull() ?: 0.0

        if (productName.isEmpty() || productDesc.isEmpty() || productPrice == 0.0) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_LONG).show()
            return
        }

        val model = ProductModel("", productName, productDesc, productPrice, url)

        productViewModel.addProduct(model) { success, message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            if (success) {
                finish()
            }
            loadingUtils.dismiss()
        }
    }
}
