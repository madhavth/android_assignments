package com.example.assignment3

import androidx.annotation.DrawableRes

data class ShoppingGridItem(
    val name: String,
    @DrawableRes val categoryIcon: Int
)