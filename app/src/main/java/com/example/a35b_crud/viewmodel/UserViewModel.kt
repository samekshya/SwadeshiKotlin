package com.example.a35b_crud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a35b_crud.model.UserModel
import com.example.a35b_crud.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class UserViewModel(private val repo: UserRepository) {

    // LiveData to observe user data
    private val _userData = MutableLiveData<UserModel?>()
    val userData: LiveData<UserModel?> get() = _userData

    // Login function
    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        repo.login(email, password, callback)
    }

    // Signup function
    fun signup(email: String, password: String, callback: (Boolean, String, String) -> Unit) {
        repo.signup(email, password, callback)
    }

    // Forgot Password function
    fun forgetPassword(email: String, callback: (Boolean, String) -> Unit) {
        repo.forgetPassword(email, callback)
    }

    // Add user to Firestore database
    fun addUserToDatabase(userId: String, userModel: UserModel, callback: (Boolean, String) -> Unit) {
        repo.addUserToDatabase(userId, userModel, callback)
    }

    // Get current Firebase user
    fun getCurrentUser(): FirebaseUser? {
        return repo.getCurrentUser()
    }

    // Fetch user data from Firestore
    fun getUserFromDatabase(userId: String) {
        repo.getUserFromDatabase(userId) { userModel, success, _ ->
            _userData.value = if (success) userModel else null
        }
    }

    // Logout function
    fun logout(callback: (Boolean, String) -> Unit) {
        repo.logout(callback)
    }

    // Edit Profile function
    fun editProfile(userId: String, data: MutableMap<String, Any>, callback: (Boolean, String) -> Unit) {
        repo.editProfile(userId, data, callback)
    }
}
