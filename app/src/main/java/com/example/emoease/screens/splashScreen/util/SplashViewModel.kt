package com.example.emoease.screens.splashScreen.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repo: SplashRepo) :ViewModel(){

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
}