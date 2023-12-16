package com.example.assignment5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.assignment5.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private var products = mutableListOf<ProductItem>()
    private lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initProducts()
        initViews()
    }

    private fun initViews() {
        adapter = ProductsAdapter(productsClicked = {
            productItem ->
            val intent = ProductDetailScreen.createIntent(this, productItem.product)
            startActivity(intent)
        }) {
            productItem ->
              products = products.map {
                  if (it.product.productId == productItem.product.productId) {
                      productItem
                  } else {
                      it
                  }
              }.toMutableList()
            adapter.quantityChanged
            adapter.submitList(products)
        }
        adapter.submitList(products)
        binding.rvProducts.adapter = adapter
        binding.btnViewCart.setOnClickListener {
            val addedToCartItems = products.filter { it.quantity > 0 }.map { it.product.productName }
            Toast.makeText(this, "$addedToCartItems", Toast.LENGTH_SHORT).show()
        }
    }
    private fun initProducts() {
        products.add(
            ProductItem(
                Product(
                    1,
                    "iPad",
                    "iPad Pro 11-inch",
                    400.0,
                    R.drawable.ipad,
                    R.drawable.apple_logo
                ),
                0
            )
        )
        products.add(
            ProductItem(
                Product(
                    2,
                    "MacBook M3 Pro",
                    "12-core CPU\n18-core GPU",
                    2500.0,
                    R.drawable.m3pro,
                    R.drawable.apple_logo
                ),
                0
            )
        )
        products.add(
            ProductItem(
                Product(
                    3,
                    "MacBook M3 Max",
                    "18-core CPU\n24-core GPU",
                    3500.0,
                    R.drawable.m3pro,
                    R.drawable.apple_logo
                ),
                0
            )
        )
        products.add(
            ProductItem(
                Product(
                    4,
                    "Dell Inspiron",
                    "Intel Core i5-1135G7\n8GB RAM\n256GB SSD",
                    1499.0,
                    R.drawable.dell_inspiron_15,
                    R.drawable.dell_logo
                ),
                0
            )
        )
        products.add(
            ProductItem(
                Product(
                    5,
                    "Logitech Keyboard",
                    "Logitech K380 Multi-Device Bluetooth Keyboard",
                    49.99,
                    R.drawable.logitech_keyboard,
                    R.drawable.logitech_logo
                ),
                0
            )
        )
         products.add(
            ProductItem(
                Product(
                    6,
                    "Logitech Mouse",
                    "Logitech MX Master 3 Advanced Wireless Mouse",
                    99.99,
                    R.drawable.logitech_mouse,
                    R.drawable.logitech_logo
                ),
                0
            )
        )
    }


}