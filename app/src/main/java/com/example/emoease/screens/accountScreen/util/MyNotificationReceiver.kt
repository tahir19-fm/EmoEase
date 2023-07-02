package com.example.emoease.screens.accountScreen.util

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.core.app.NotificationCompat
import com.example.emoease.MainActivity
import com.example.emoease.R
import com.example.emoease.screens.accountScreen.data.NotificationDAO
import kotlin.random.Random

class MyNotificationReceiver : BroadcastReceiver() {
    private val channelId = "reminder" // Unique channel ID
    private val notificationId = 1
    override fun onReceive(context: Context, intent: Intent) {
        // Create and show the notification
        val title=intent.getStringExtra("title")
        val content=intent.getStringExtra("content")
        val notification = createNotification(context,NotificationDAO(title?:"reminder",content?:"description"))
        showNotification(context, notification)
    }

    private fun createNotification(context: Context, notificationDAO: NotificationDAO): Notification {
        // Create the notification using NotificationCompat.Builder
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(notificationDAO.title)
            .setContentText(notificationDAO.content)
            .setSmallIcon(R.drawable.download)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.happy_login_img))
            .setAutoCancel(true)
//            .setColor() // Set the color of the notification
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Set the priority of the notification
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // Set the notification sound
            .setVibrate(longArrayOf(1000, 1000, 1000))
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(createPendingIntent(context))
        // Customize other notification properties as needed

        return notificationBuilder.build()
    }

    private fun showNotification(context: Context, notification: Notification) {
        // Show the notification using the NotificationManager
        val notificationManager =
            context.getSystemService(NotificationManager::class.java) as NotificationManager
        notificationManager.notify(Random.nextInt(), notification)
    }
}
private fun createPendingIntent(context: Context): PendingIntent {
    // Create an explicit intent to launch a specific composable screen or perform an action
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // Add any extra data if needed
    }
    return PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}


