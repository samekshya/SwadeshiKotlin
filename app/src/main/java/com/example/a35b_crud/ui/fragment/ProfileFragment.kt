package com.example.a35b_crud.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a35b_crud.R
import com.example.a35b_crud.databinding.FragmentProfileBinding
import com.example.a35b_crud.repository.UserRepositoryImpl
import com.example.a35b_crud.ui.activity.LoginActivity
import com.example.a35b_crud.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        val currentUser = userViewModel.getCurrentUser()

        if (currentUser != null) {
            userViewModel.getUserFromDatabase(currentUser.uid)
        } else {
            Toast.makeText(requireContext(), "No user logged in", Toast.LENGTH_SHORT).show()
        }

        userViewModel.userData.observe(viewLifecycleOwner) { users ->
            if (users != null) {
                binding.profileEmail.text = users.email
                binding.profileName.text = "${users.firstName} ${users.lastName}"
            } else {
                Toast.makeText(requireContext(), "User data not found", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle Logout Click
        binding.logout.setOnClickListener {
            userViewModel.logout { success, message ->
                if (success) {
                    Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                } else {
                    Toast.makeText(requireContext(), "Logout failed: $message", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
