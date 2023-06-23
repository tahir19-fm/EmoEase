package com.example.emoease.screens.bottomBar

import androidx.annotation.DrawableRes
import com.example.emoease.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val iconFilled: Int,
    @DrawableRes val iconOutlined:Int
){
    object Home : BottomBarScreen(route = "home" , "Home", R.drawable.truck_solid,R.drawable.truck_outline)
    object History : BottomBarScreen(route = "history" , "Activity" ,R.drawable.solid_clock,R.drawable.outline_clock)
    object Account : BottomBarScreen(route = "account", "Account" ,R.drawable.solid_user,R.drawable.outlined_user)
}