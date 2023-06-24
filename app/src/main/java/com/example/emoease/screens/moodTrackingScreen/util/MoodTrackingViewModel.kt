package com.example.emoease.screens.moodTrackingScreen.util

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emoease.roomDb.ActivityModal
import com.example.emoease.roomDb.EmotionModal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
        viewModelScope.launch {
           val r= repository.getActivityItems("Activities")
            Timber.tag("roomResult").d("${r.items}")
        }
    }
   fun saveMood(emotionModal: EmotionModal){
       viewModelScope.launch {
           repository.saveMood(emotionModal)
       }
   }
}