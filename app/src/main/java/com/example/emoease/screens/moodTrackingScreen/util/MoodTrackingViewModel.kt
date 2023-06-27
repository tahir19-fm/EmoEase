package com.example.emoease.screens.moodTrackingScreen.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emoease.networkService.ApiResult
import com.example.emoease.roomDb.ActivityModal
import com.example.emoease.roomDb.EmotionModal
import com.example.emoease.utils.stringToList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoodTrackingViewModel @Inject constructor(private val repository: MoodTrackingRepository) :ViewModel() {
    init {

        viewModelScope.launch {
            repository.saveActivityItem(ActivityModal(Constants.Symptoms, emptyList()))
        }

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
    fun updateActivityItem(id: String,newItem:String){
        val list= activityItems.value?.data?.items as List<String>
        val newList=ArrayList<String>()
        newList.addAll(list)
        newList.add(newItem)
        viewModelScope.launch{
            repository.updateActivityColumn(id,newList)
        }.invokeOnCompletion {
            getActivityItems(id)
        }
    }


    private val _selectedItems = MutableLiveData<ApiResult<List<String>>>()
    val selectedItems : MutableLiveData<ApiResult<List<String>>>
        get() = _selectedItems
    fun selectedItems(list:List<String>,id: String){
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
        _selectedItems.value=ApiResult.Loading
        viewModelScope.launch {
           _selectedItems.value= repository.getSelectedActivityItems(id, todayDate())
            if (selectedItems.value?.data?.size ==1){
                _selectedItems.value=ApiResult.Success(stringToList(selectedItems.value!!.data?.get(0) ?: ""))
            }
            for (item in selectedItems.value?.data!!){
                Timber.tag("selectredItems").d(item)
            }
        }
    }

    fun checkSelected(item: String): Boolean {
        val data=selectedItems.value?.data as List<String>
        if (data.contains(item)){
            return true
        }
        return false
    }



}