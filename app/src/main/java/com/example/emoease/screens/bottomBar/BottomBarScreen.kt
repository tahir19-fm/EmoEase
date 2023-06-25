package com.example.emoease.screens.bottomBar

import androidx.annotation.DrawableRes
import com.example.emoease.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val iconFilled: Int,
    @DrawableRes val iconOutlined:Int
){
    object Home : BottomBarScreen(route = "home" , "Home", R.drawable.face_smile_solid,R.drawable.face_smile_outline)
    object History : BottomBarScreen(route = "history" , "Activity" ,R.drawable.solid_clock,R.drawable.outline_clock)
    object Account : BottomBarScreen(route = "account", "Account" ,R.drawable.solid_user,R.drawable.outlined_user)
    object Exercise : BottomBarScreen(route = "exercise", "Exercise" ,R.drawable.exercise_filled,R.drawable.outline_exercise)
}