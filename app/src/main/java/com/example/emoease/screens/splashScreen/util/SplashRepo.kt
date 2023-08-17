package com.example.emoease.screens.splashScreen.util

import com.example.emoease.networkService.ApiResult
import com.example.emoease.networkService.CommonApiService
import com.example.emoease.networkService.networkCall
import com.example.emoease.roomDb.AuthSharedPreferences
import com.example.emoease.roomDb.modals.ActivityModal
import com.example.emoease.roomDb.modals.OneTimeService
import com.example.emoease.roomDb.dao.OneTimeServiceDao
import com.example.emoease.screens.authScreen.data.AuthResponceDAO
import com.example.emoease.screens.moodTrackingScreen.util.Constants
import com.example.emoease.screens.moodTrackingScreen.util.listOfActivities
import com.example.emoease.screens.moodTrackingScreen.util.listOfSleep
import com.example.emoease.screens.moodTrackingScreen.util.listOfSocial
import javax.inject.Inject

class SplashRepo @Inject constructor(private val oneTimeService: OneTimeServiceDao,private val api:CommonApiService) {


    suspend fun insertIstTime(){
        try {
            oneTimeService.insert(OneTimeService(exists = true))
        }catch (e:Exception){
            return
        }
    }
    suspend fun insertActivities(){
        try {
            oneTimeService.insertActivities(listOf(
                ActivityModal(Constants.Activities,
                listOfActivities),
                ActivityModal(Constants.Social,
                    listOfSocial),
                ActivityModal(Constants.Sleep,
                    listOfSleep),
                ActivityModal(Constants.Symptoms,
                    listOf("")
                ),
            ))
        }catch (e:Exception){
            return
        }
    }
    suspend fun exists():Boolean{
        return try {

            oneTimeService.exists()

        }catch (e:Exception){
            false
        }
    }
    suspend fun getNewAuthToken(refreshToken:String): AuthResponceDAO?{
        return when(val response= networkCall(api.loginUser("",""))){
            is ApiResult.Success->{
                response.data as AuthResponceDAO
            }
            else -> {
                null
            }
        }
    }

}