package com.example.emoease.screens.splashScreen.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emoease.EmoEaseApp
import com.example.emoease.roomDb.AuthSharedPreferences
import com.example.emoease.screens.authScreen.data.AuthData
import com.example.emoease.screens.authScreen.data.AuthResponceDAO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repo: SplashRepo) :ViewModel(){

    init {
        Log.d("TAG", ":shjdbkfj ")

    }

    fun initIstTime(){
        viewModelScope.launch {
            repo.insertIstTime()
        }
    }
    fun initActivity(){
        viewModelScope.launch {
            repo.insertActivities()
        }
    }
    private val _exists=MutableLiveData<Boolean>()
    val exist:MutableLiveData<Boolean>
        get() = _exists
     fun exists() {
        viewModelScope.launch {
            _exists.value=repo.exists()
        }
    }
     fun getNewAuthToken(refreshToken:String){
        var data=AuthResponceDAO(false, AuthData(EmoEaseApp.unauthorisedAuthToken,"",0),"")
//        viewModelScope.launch {
//           val response= repo.getNewAuthToken(refreshToken = refreshToken)
//            if (response!=null){
//                data=response
//            }
//        }TODO:sdfsdf
         if (data.success==true) {
             AuthSharedPreferences.authToken = data.data?.authToken
             AuthSharedPreferences.tokenExpiry =
                 (data.data?.expiresIn!! * 1000) + System.currentTimeMillis()
             AuthSharedPreferences.refreshToken = data.data?.refreshToken
         }

    }
}