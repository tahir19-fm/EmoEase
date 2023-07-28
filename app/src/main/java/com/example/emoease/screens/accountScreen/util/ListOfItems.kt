package com.example.emoease.screens.accountScreen.util


import com.example.emoease.R
import com.example.emoease.screens.accountScreen.data.AccountItem
import com.example.emoease.ui.theme.AboutColor
import com.example.emoease.ui.theme.AppLanguageColor
import com.example.emoease.ui.theme.LogoutColor
import com.example.emoease.ui.theme.PaymentColor
import com.example.emoease.ui.theme.ProfileColor
import com.example.emoease.ui.theme.SupportColor

val othersList = listOf(
    AccountItem("Support", R.drawable.new_support_icon, SupportColor,"How can we help you?"),
//    AccountItem("Logout", R.drawable.new_logout_icon, LogoutColor,null)
)
val genralList= listOf(
    AccountItem("Profile", R.drawable.new_profile_icon, ProfileColor,"8082731286",true),
    AccountItem("Payments", R.drawable.new_payment_icon, PaymentColor,"Add or change payment methods"),
)
val applicationList= listOf(
    AccountItem("App Language", R.drawable.new_language_icon, AppLanguageColor,"English"),
    AccountItem("About", R.drawable.new_about_icon, AboutColor,"2.12.3"),
)

