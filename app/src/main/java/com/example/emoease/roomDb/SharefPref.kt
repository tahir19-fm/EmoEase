package com.example.emoease.roomDb

import android.content.Context
import android.content.SharedPreferences
import com.example.emoease.EmoEaseApp

object AuthSharedPreferences {

        private const val PREF_NAME = "auth_prefs"
        private const val KEY_AUTH_TOKEN = "auth_token"
        private const val KEY_TOKEN_EXPIRY = "token_expiry"
        private const val KEY_REFRESH_TOKEN = "refresh_token"

    private val context by lazy { EmoEaseApp.instance?.baseContext }
    private val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var authToken: String?
        get() = sharedPreferences.getString(KEY_AUTH_TOKEN, null)
        set(value) {
            sharedPreferences.edit().putString(KEY_AUTH_TOKEN, value).apply()
        }

    var tokenExpiry: Long
        get() = sharedPreferences.getLong(KEY_TOKEN_EXPIRY, 0)
        set(value) {
            sharedPreferences.edit().putLong(KEY_TOKEN_EXPIRY, value).apply()
        }

    var refreshToken: String?
        get() = sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
        set(value) {
            sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, value).apply()
        }

    fun clearAuthToken() {
        sharedPreferences.edit().remove(KEY_AUTH_TOKEN).apply()
    }
}
