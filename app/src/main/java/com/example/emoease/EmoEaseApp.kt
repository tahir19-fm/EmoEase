package com.example.emoease

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.location.LocationManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.lang.Appendable

@HiltAndroidApp
class EmoEaseApp:Application() {
    companion object {

        @JvmStatic
        var instance: EmoEaseApp? = null

    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        val notificationChannel=NotificationChannel(
            "reminder",
            "reminder channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)
    }
}