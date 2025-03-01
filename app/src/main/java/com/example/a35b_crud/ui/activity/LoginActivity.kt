package com.example.a35b_crud.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a35b_crud.databinding.ActivityLoginBinding
import com.example.a35b_crud.utils.LoadingUtils
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

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

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    loadingUtils.dismiss()

                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, NavigationActivity::class.java))
                        finish()
                    } else {
                        val errorMessage = task.exception?.message ?: "Unknown error"
                        Toast.makeText(this, "Login failed: $errorMessage", Toast.LENGTH_LONG).show()
                        println("DEBUG: Firebase Login Error -> $errorMessage")
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
    }
}
