package com.example.emoease.networkService



import com.example.emoease.screens.exerciseScreen.modal.YogaCategories
import retrofit2.Response
import retrofit2.http.GET

interface CommonApiService {
    @GET("/v1/categories")
    suspend fun getCategories():Response<List<YogaCategories>>

}