package com.example.emoease.networkService



import com.example.emoease.screens.authScreen.data.AuthResponceDAO
import com.example.emoease.screens.authScreen.data.LoginDAO
import com.example.emoease.screens.exerciseScreen.modal.YogaCategories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommonApiService {
    @GET("/Prod/v1/categories")
    suspend fun getCategories():Response<List<YogaCategories>>

    @POST("/Prod/login")
    suspend fun loginUser(@Query("username") userName:String,@Query("password") password:String):Response<AuthResponceDAO>

}