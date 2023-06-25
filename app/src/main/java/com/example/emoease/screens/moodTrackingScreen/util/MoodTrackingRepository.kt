package com.example.emoease.screens.moodTrackingScreen.util

import androidx.room.RoomDatabase
import com.example.emoease.networkService.ApiResult
import com.example.emoease.roomDb.ActivityModal
import com.example.emoease.roomDb.AppDatabaseDao
import com.example.emoease.roomDb.EmotionModal
import com.example.emoease.screens.moodTrackingScreen.data.MoodEntry
import javax.inject.Inject

class MoodTrackingRepository @Inject constructor(private val roomDatabase: AppDatabaseDao) {
    suspend fun saveMood(moodEntry: EmotionModal){
        roomDatabase.saveMood(moodEntry)
    }
    suspend fun saveActivityItem(activityModal: ActivityModal){
        roomDatabase.insert(activityModal = activityModal)
    }
    suspend fun getActivityItems(id:String):ApiResult<ActivityModal>{
      return  ApiResult.Success(roomDatabase.getActivityItems(id))
    }
    suspend fun getEmotionDetails(id: String):ApiResult<EmotionModal>{
        return ApiResult.Success(roomDatabase.getEmotionDetails(id))
    }

    suspend fun updateMood(newMood:Int,id: String){
        return roomDatabase.updateMood(newMood,id)
    }

    suspend fun getMood(id: String): ApiResult.Success<Int> {
        return ApiResult.Success(roomDatabase.getMood(id))
    }
}