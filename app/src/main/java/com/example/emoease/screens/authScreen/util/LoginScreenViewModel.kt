package com.example.emoease.screens.authScreen.util

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emoease.EmoEaseApp
import com.example.emoease.networkService.ApiResult
import com.example.emoease.roomDb.AuthSharedPreferences
import com.example.emoease.screens.authScreen.data.AuthResponceDAO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val repository: LoginRepository) :ViewModel() {
    private val auth:FirebaseAuth=Firebase.auth
    private val _loading=MutableLiveData(false)
    val loading:LiveData<Boolean> =_loading

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit,error:(String)->Unit)
            = viewModelScope.launch{
        _loading.value=true
        try {
//            auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful){
//                        _loading.value=false
//                        Timber.tag("Firebase").d("Success")
//                        home()
//                    }
//                }
//                .addOnFailureListener{
//                    _loading.value=false
//                    Timber.tag("Firebase").d("$it")
//                    it.localizedMessage?.let { it1 -> error(it1) }
//                }
            when(val result=repository.loginUser(email,password)){
                is ApiResult.Success->{
                   val data= result.data as AuthResponceDAO
                    AuthSharedPreferences.authToken= data.data?.authToken
                    AuthSharedPreferences.refreshToken= data.data?.refreshToken
                    AuthSharedPreferences.tokenExpiry= (data.data?.expiresIn!!*1000)+System.currentTimeMillis()

                    Log.d("TAG", "signInWithEmailAndPassword: ${AuthSharedPreferences.authToken}")
                }

                else -> {}
            }

        }catch (ex: Exception){
            _loading.value=false
            Timber.tag("Firebase").e(ex)
            ex.localizedMessage?.let { error(it)
            }
        }


    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit,
    error: (String) -> Unit) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        home()
                    }else {
                        error(task.result)

                    }
                    _loading.value = false


                }
                .addOnFailureListener{
                    _loading.value=false
                    it.localizedMessage?.let { it1 -> error(it1) }
                }
        }


    }

    private fun createUser(displayName: String?) {
//        val userId = auth.currentUser?.uid
//
//        val user = MUser(userId = userId.toString(),
//            displayName = displayName.toString(),
//            avatarUrl = "",
//            quote = "Life is great",
//            profession = "Android Developer",
//            id = null).toMap()
//
//
//        FirebaseFirestore.getInstance().collection("users")
//            .add(user)

    }



}