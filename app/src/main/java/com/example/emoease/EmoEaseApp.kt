package com.example.emoease

import android.app.Application
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
    }
}