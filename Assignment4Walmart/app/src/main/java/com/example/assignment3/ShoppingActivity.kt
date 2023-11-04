package com.example.assignment3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ShoppingActivity : AppCompatActivity() {

    companion object {
        const val USERNAME = "USERNAME"

        fun createIntent(context: Context, userName: String): Intent {
            return Intent(context, ShoppingActivity::class.java).apply {
                putExtra(USERNAME, userName)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
    }
}