package com.example.emoease.utils

import android.util.Log
import com.example.emoease.EmoEaseApp
import com.example.emoease.roomDb.AuthSharedPreferences


    fun isValidFirebaseAuthTokenSaved(): Boolean {
        Log.d("dsjkbv", "isValidFirebaseAuthTokenSaved:")
        val tokenExpirey = AuthSharedPreferences.tokenExpiry
        //check is last token saved time < 50 minutes.
        if (System.currentTimeMillis() < tokenExpirey) {
            AuthSharedPreferences.authToken.let {
                if (it == EmoEaseApp.unauthorisedAuthToken)
                    return false
                else {
                    if (it != null) {
                        EmoEaseApp.authToken=it
                    }
                    return true
                }
            }
        }
        return false
    }