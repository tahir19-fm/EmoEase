package com.example.emoease

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.location.LocationManager
import com.example.emoease.roomDb.AuthSharedPreferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Appendable

@HiltAndroidApp
class EmoEaseApp:Application() {
    companion object {

        @JvmStatic
        var instance: EmoEaseApp? = null
        var unauthorisedAuthToken = "unauthorisedToken"
        var authToken: String = unauthorisedAuthToken

    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        val notificationChannel=NotificationChannel(
            "newReminder",
            "reminder channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}