package com.example.assignment3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val users = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUsers()
        initViews()
    }

    private fun initViews() {
        binding.btnSignIn.setOnClickListener {
            val username = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()

            if (username.isBlank() || password.isBlank()) {
                showToast("Please fill in all fields")
                return@setOnClickListener
            }

            if (users.any { user -> user.userName == username && user.password == password }) {
                showToast("Login success for $username")
            } else {
                showToast("Invalid credentials, please try again.")
                binding.etPass.text?.clear()
            }
        }
    }

    private fun initUsers() {
        for (i in 1..5) {
            users.add(User("User$i", "User$i", "User$i@gmail.com", "User$i"))
        }
    }
}
