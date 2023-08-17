package com.example.emoease.screens.authScreen.data

import com.google.gson.annotations.SerializedName

data class LoginDAO (
    @SerializedName("username")
    val userName: String? =null,
    @SerializedName("password")
    val password:String?=null
    )