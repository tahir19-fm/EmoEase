package com.example.emoease.screens.moodTrackingScreen.util

import androidx.room.RoomDatabase
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
    suspend fun getActivityItems(id:String):ActivityModal{
      return  roomDatabase.getActivityItems(id)
    }
}