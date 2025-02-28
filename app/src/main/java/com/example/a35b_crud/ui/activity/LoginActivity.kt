package com.example.a35b_crud.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a35b_crud.R
import com.example.a35b_crud.databinding.ActivityLoginBinding
import com.example.a35b_crud.repository.UserRepositoryImpl
import com.example.a35b_crud.utils.LoadingUtils
import com.example.a35b_crud.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Repository & ViewModel
        val repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        // Initialize Loading Utils
        loadingUtils = LoadingUtils(this)

        // Handle Login Button Click
        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            loadingUtils.show()

            // Ensure login process does not hang indefinitely
            userViewModel.login(email, password) { success, message ->
                runOnUiThread {
                    loadingUtils.dismiss()
                    if (success) {
                        Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@LoginActivity, NavigationActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed: $message", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        // Handle Sign-Up Navigation
        binding.btnSignupnavigate.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Handle Forgot Password
        binding.btnForget.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }

        // Handle System Insets for UI Adjustment
        val mainView = findViewById<View?>(R.id.main)
        mainView?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}
