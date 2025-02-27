package com.example.a35b_crud.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a35b_crud.R
import com.example.a35b_crud.databinding.ActivityForgetPasswordBinding
import com.example.a35b_crud.repository.UserRepositoryImpl
import com.example.a35b_crud.utils.LoadingUtils
import com.example.a35b_crud.viewmodel.UserViewModel

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var forgetPasswordBinding: ActivityForgetPasswordBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forgetPasswordBinding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(forgetPasswordBinding.root)

        // Initialize ViewModel & Repository
        val repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        // Initialize Loading Utils
        loadingUtils = LoadingUtils(this)

        // Handle Reset Password Button Click
        forgetPasswordBinding.btnForget.setOnClickListener {
            val email = forgetPasswordBinding.editEmailForget.text.toString().trim()

            // Validate email input
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Show loading
            loadingUtils.show()

            // Call ViewModel function
            userViewModel.forgetPassword(email) { success, message ->
                loadingUtils.dismiss()
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                if (success) {
                    finish() // Close the activity if reset email was sent successfully
                }
            }
        }

        // Handle Window Insets for Edge-to-Edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
