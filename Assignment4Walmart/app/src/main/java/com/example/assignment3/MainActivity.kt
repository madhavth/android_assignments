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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.btnAdd.setOnClickListener { onBtnAddClicked() }
    }

    private fun onBtnAddClicked() {
        val androidVersion: String = binding.etAndroidVersion.text.toString()
        val androidCode: String = binding.etAndroidCode.text.toString()

        if(androidVersion.isEmpty() || androidCode.isEmpty()) {
            showToast("Android version/ code is empty.")
            return
        }

        addToTable(androidVersion, androidCode)
    }

    private fun addToTable(androidVersion: String, androidCode: String) {
        val tableRow = TableRow(this).apply {
            setBackgroundColor(resources.getColor(R.color.pink))
        }
        tableRow.layoutParams = TableRow.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        tableRow.addView(createTextView(androidVersion))
        tableRow.addView(createTextView(androidCode))
        tableRow.requestLayout()

        binding.tableLayout.addView(tableRow)
        binding.tableLayout.requestLayout()

        showToast("Added $androidVersion and $androidCode to table successfully.")
    }

    private fun createTextView(text: String): View {
        val textView = TextView(this)
        textView.text = text
        return textView
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}