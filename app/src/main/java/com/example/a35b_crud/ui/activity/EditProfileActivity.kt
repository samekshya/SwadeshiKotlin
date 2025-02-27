package com.example.a35b_crud.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a35b_crud.databinding.ActivityEditProfileBinding
import com.example.a35b_crud.repository.UserRepositoryImpl
import com.example.a35b_crud.viewmodel.UserViewModel

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = UserViewModel(UserRepositoryImpl())

        // Load current user data
        val currentUser = userViewModel.getCurrentUser()

        if (currentUser != null) {
            userViewModel.getUserFromDatabase(currentUser.uid)
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show()
            finish() // Close activity if no user is logged in
            return
        }

        userViewModel.userData.observe(this) { user ->
            if (user != null) {
                binding.editFirstName.setText(user.firstName)
                binding.editLastName.setText(user.lastName)
                binding.editAddress.setText(user.address)
                binding.editPhone.setText(user.phoneNumber)
            } else {
                Toast.makeText(this, "Failed to load user data", Toast.LENGTH_SHORT).show()
            }
        }

        // Save Button Click
        binding.btnSaveProfile.setOnClickListener {
            val updatedData = mutableMapOf<String, Any>(
                "firstName" to binding.editFirstName.text.toString(),
                "lastName" to binding.editLastName.text.toString(),
                "address" to binding.editAddress.text.toString(),
                "phoneNumber" to binding.editPhone.text.toString()
            )

            // Check again if currentUser is valid before updating profile
            if (currentUser != null) {
                userViewModel.editProfile(currentUser.uid, updatedData) { success, message ->
                    if (success) {
                        Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Update Failed: $message", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Error: User not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
