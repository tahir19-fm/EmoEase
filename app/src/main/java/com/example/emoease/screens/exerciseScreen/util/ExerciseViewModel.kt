package com.example.emoease.screens.exerciseScreen.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emoease.networkService.ApiResult
import com.example.emoease.roomDb.ActivityModal
import com.example.emoease.screens.exerciseScreen.modal.YogaCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(private val repository: ExerciseRepository):ViewModel() {
    private val _categories = MutableLiveData<ApiResult<List<YogaCategories>>>()
    val categories : MutableLiveData<ApiResult<List<YogaCategories>>>
        get() = _categories
    fun getActivityItems(){
        _categories.value= ApiResult.Loading
        viewModelScope.launch{
            _categories.value= repository.getCategories()
        }
        Timber.tag("categoriesData").d(categories.toString())
    }
    private val _categoryItems=MutableLiveData<YogaCategories>()
    val categoryItems:MutableLiveData<YogaCategories>
        get() = _categoryItems
    fun setCategoryItems(items:YogaCategories){
        _categoryItems.value=items
    }

}