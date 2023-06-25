package com.example.emoease.screens.moodTrackingScreen.util

import android.icu.text.CaseMap.Title
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emoease.networkService.ApiResult
import com.example.emoease.roomDb.ActivityModal
import com.example.emoease.roomDb.EmotionModal
import com.google.protobuf.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoodTrackingViewModel @Inject constructor(private val repository: MoodTrackingRepository) :ViewModel() {
    init {
//        viewModelScope.launch{
//            val list= listOfActivities.toMutableList()
//            list.add("🏑KhoKho")
//            repository.saveActivityItem(ActivityModal(
//                "Activities",
//                list
//            ))
//        }
//        viewModelScope.launch {
//           val r= repository.getActivityItems("Activities")
//            Timber.tag("roomResult").d("${r.items}")
//        }
    }
    fun saveMood(emotionModal: EmotionModal){
       viewModelScope.launch {
           repository.saveMood(emotionModal)
       }
    }
    private val _activityItems = MutableLiveData<ApiResult<ActivityModal>>()
    val activityItems : MutableLiveData<ApiResult<ActivityModal>>
        get() = _activityItems
    fun getActivityItems(title: String){
        _activityItems.value=ApiResult.Loading
        viewModelScope.launch{
           _activityItems.value= repository.getActivityItems(title)
        }
    }
    fun saveActivityItem(title: String,item:String){
        viewModelScope.launch{
            val list= listOfActivities.toMutableList()
            list.add(item)
            repository.saveActivityItem(
                ActivityModal(
                    title,
                    list
                )
            )
        }
    }
    private val _emotionDetails = MutableLiveData<ApiResult<EmotionModal>>()
    val emotionDetails : MutableLiveData<ApiResult<EmotionModal>>
        get() = _emotionDetails
    fun getEmotionDetails(id:String){
        _emotionDetails.value=ApiResult.Loading
        viewModelScope.launch {
            _emotionDetails.value=repository.getEmotionDetails(id)
        }
    }
   private val _mood=MutableLiveData<ApiResult<Int>>()
    val mood:MutableLiveData<ApiResult<Int>>
        get() = _mood

    fun getMood(id: String){
        viewModelScope.launch {
            _mood.value=ApiResult.Loading
            _mood.value=repository.getMood(id)
            Timber.tag("getMood").d(mood.toString())
        }
    }
    fun updateMood(mood:Int,id: String){
        viewModelScope.launch {
            repository.updateMood(mood, id = id)
        }.invokeOnCompletion {
            getMood(id)
        }
    }
    private fun updateActivities(list: List<String>, id: String){
        viewModelScope.launch {
            repository.updateActivities(list,id)
        }.invokeOnCompletion {
            getEmotionDetails(id)
        }
    }
    private fun updateSocials(list: List<String>, id: String){
        viewModelScope.launch {
            repository.updateSocials(list,id)
        }.invokeOnCompletion {
            getMood(id)
        }
    }
    private fun updateSleep(list: List<String>, id: String){
        viewModelScope.launch {
            repository.updateSleep(list,id)
        }.invokeOnCompletion {
            getMood(id)
        }
    }
    private fun updateSymptoms(list: List<String>, id: String){
        viewModelScope.launch {
            repository.updateSymptoms(list,id)
        }.invokeOnCompletion {
            getMood(id)
        }
    }


    private val _selectedItems = MutableLiveData<List<String>>()
    val selectedItems : MutableLiveData<List<String>>
        get() = _selectedItems
    fun selectedItems(list:List<String>,id: String){
        _selectedItems.value=list
        when(id){
            Constants.Activities->{
                updateActivities(list, todayDate())
            }
            Constants.Social->{
                updateSocials(list = list, todayDate())
            }
            Constants.Sleep->{
                updateSleep(list, todayDate())
            }
            Constants.Symptoms->{
                updateSymptoms(list = list, todayDate())
            }
        }
    }


}