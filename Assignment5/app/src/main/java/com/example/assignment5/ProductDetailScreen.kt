package com.example.assignment5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.example.assignment5.databinding.ActivityProductDetailScreenBinding

class ProductDetailScreen : AppCompatActivity() {

    private var product: Product? = null

    companion object {
        const val PRODUCT = "PRODUCT"
        fun createIntent(context: Context, product: Product): Intent {
            val intent = Intent(context, ProductDetailScreen::class.java)
            intent.putExtra(PRODUCT, product)
            return intent
        }
    }

    private lateinit var binding: ActivityProductDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        product = intent.getParcelableExtra(PRODUCT)
        initViews()
    }

    private fun initViews() {
        binding.btnHome.setOnClickListener {
            finish()
        }

        if (product != null) {
            binding.tvProductName.text = product?.productName
            binding.tvProductDescription.text = product?.productDescription
            binding.tvProductPrice.text = "$ ${product?.cost.toString()}"
            binding.ivProductImage.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    product!!.productImage
                )
            )
        }
    }
}