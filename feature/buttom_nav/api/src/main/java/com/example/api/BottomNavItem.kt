package com.example.api

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val iconResId: Int
)
