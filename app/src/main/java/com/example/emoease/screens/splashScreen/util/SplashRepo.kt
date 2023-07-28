package com.example.emoease.screens.splashScreen.util

import com.example.emoease.roomDb.ActivityModal
import com.example.emoease.roomDb.OneTimeService
import com.example.emoease.roomDb.OneTimeServiceDao
import com.example.emoease.screens.moodTrackingScreen.util.Constants
import com.example.emoease.screens.moodTrackingScreen.util.listOfActivities
import com.example.emoease.screens.moodTrackingScreen.util.listOfSleep
import com.example.emoease.screens.moodTrackingScreen.util.listOfSocial
import javax.inject.Inject

class SplashRepo @Inject constructor(private val oneTimeService: OneTimeServiceDao) {
    suspend fun insertIstTime(){
        try {
            oneTimeService.insert(OneTimeService(exists = true))
        }catch (e:Exception){
            return
        }
    }
    suspend fun insertActivities(){
        try {
            oneTimeService.insertActivities(listOf(ActivityModal(Constants.Activities,
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

}