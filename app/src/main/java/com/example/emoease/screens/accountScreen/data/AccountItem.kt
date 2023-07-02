package com.example.emoease.screens.accountScreen.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class AccountItem(
    val itemName:String, @DrawableRes val itemIcon:Int,
    val color: Color,
    val itemDescription: String?,
    val enabled : Boolean = false
)

