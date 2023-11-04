package com.example.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import com.example.assignment3.databinding.ActivityMainBinding
import com.example.assignment3.databinding.ActivityWalmartLoginBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWalmartLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalmartLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {

    }



    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}