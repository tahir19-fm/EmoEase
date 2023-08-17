package com.example.emoease.screens.authScreen.data

import com.google.gson.annotations.SerializedName

data class AuthResponceDAO (
    @SerializedName("success")
    val success:Boolean?=null,
    @SerializedName("data")
    val data:AuthData?=null,
    @SerializedName("message")
    val message:String?=null
        )
data class AuthData(
    @SerializedName("authToken")
    val authToken:String?=null,
    @SerializedName("refreshToken")
    val refreshToken:String?=null,
    @SerializedName("expiresIn")
    val expiresIn:Long?=null
)