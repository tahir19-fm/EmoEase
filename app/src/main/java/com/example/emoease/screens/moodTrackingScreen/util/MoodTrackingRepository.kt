package com.example.emoease.screens.moodTrackingScreen.util

import com.example.emoease.networkService.ApiResult
import com.example.emoease.roomDb.modals.ActivityModal
import com.example.emoease.roomDb.dao.AppDatabaseDao
import com.example.emoease.roomDb.modals.EmotionModal
import javax.inject.Inject

class MoodTrackingRepository @Inject constructor(private val roomDatabase: AppDatabaseDao) {
    suspend fun saveMood(moodEntry: EmotionModal){
        roomDatabase.saveMood(moodEntry)
    }
    suspend fun saveActivityItem(activityModal: ActivityModal){
        roomDatabase.insert(activityModal = activityModal)
    }
    suspend fun getEmotionHistory(): ApiResult<List<EmotionModal>> {
        try {
            val result = roomDatabase.getEmotionHistory() // Execute Room query
            return if (result.isNotEmpty()){
                ApiResult.Success(result)
            }else{
                ApiResult.Error("No Data Found")
            }
            // Handle the result
        } catch (e: Exception) {
            // Handle the error
            e.localizedMessage?.let { ApiResult.Error(it) }
        }
        return ApiResult.Error("Execution Failed")
    }

    suspend fun getListByMood(mood:Int): ApiResult<List<EmotionModal>>? {
        return try {
            val result=roomDatabase.getListByMood(mood = mood)
            if (result.isNotEmpty()){
                ApiResult.Success(result)
            }else{
                ApiResult.Error("Empty List")
            }
        }catch (e:Exception){
            e.localizedMessage?.let { ApiResult.Error(it) }
        }
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

    suspend fun updateActivities(newValue:List<String>,id: String){
        return roomDatabase.updateActivities(newValue,id)
    }
    suspend fun updateSocials(newValue:List<String>,id: String){
        return roomDatabase.updateSocials(newValue,id)
    }
    suspend fun updateSleep(newValue:List<String>,id: String){
        return roomDatabase.updateSleep(newValue,id)
    }
    suspend fun updateSymptoms(newValue:List<String>,id: String){
        return roomDatabase.updateSymptoms(newValue,id)
    }

    suspend fun updateActivityColumn(activityName: String,newValue: List<String>){
        roomDatabase.updateActivityColumnValue(activityName,newValue)
    }
    suspend fun getSelectedActivityItems(activityName:String,id: String):ApiResult<List<String>>{
        when(activityName){
            Constants.Activities->{
                return ApiResult.Success(roomDatabase.getSelectedActivity(id))
            }
            Constants.Social->{
                return ApiResult.Success(roomDatabase.getSelectedSocial(id))
            }
            Constants.Sleep->{
                return ApiResult.Success(roomDatabase.getSelectedSleep(id))
            }
            Constants.Symptoms->{
                return ApiResult.Success(roomDatabase.getSelectedSymptoms(id))
            }
        }
        return ApiResult.Error("No data found")
    }

    suspend fun dataExists(id: String):ApiResult<Boolean>{
        return ApiResult.Success(roomDatabase.existsById(id))
    }
    suspend fun updateNotes(newNotes:String,id: String){
        return roomDatabase.updateNotes(newNote = newNotes, id = id)
    }
}