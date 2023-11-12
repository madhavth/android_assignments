package com.example.assignment5

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val productId: Int,
    val productName: String,
    val productDescription: String,
    val cost: Double,
    @DrawableRes val productImage: Int = 0,
    @DrawableRes val productLogo: Int =0,
    ): Parcelable