package com.example.emoease.screens.exerciseScreen.util

import com.example.emoease.networkService.ApiResult
import com.example.emoease.networkService.CommonApiService
import com.example.emoease.networkService.networkCall
import com.example.emoease.screens.exerciseScreen.modal.YogaCategories
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val commonApiService: CommonApiService) {
    suspend fun getCategories():ApiResult<List<YogaCategories>>{
        return networkCall(commonApiService.getCategories())
    }
}