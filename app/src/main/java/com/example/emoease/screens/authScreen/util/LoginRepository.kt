package com.example.emoease.screens.authScreen.util

import com.example.emoease.networkService.ApiResult
import com.example.emoease.networkService.CommonApiService
import com.example.emoease.networkService.networkCall
import com.example.emoease.screens.authScreen.data.AuthResponceDAO
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api:CommonApiService) {
    suspend fun loginUser(email:String,password:String): ApiResult<AuthResponceDAO> {
       return networkCall(api.loginUser(email,password))
    }
}