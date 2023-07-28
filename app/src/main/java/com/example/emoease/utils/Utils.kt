package com.example.emoease.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Locale

fun Modifier.clickableWithoutRipple(
    interactionSource: MutableInteractionSource = MutableInteractionSource(), onClick: () -> Unit
) = composed(factory = {
    this.then(Modifier.clickable(interactionSource = interactionSource,
        indication = null,
        onClick = { onClick() }))
})

fun stringToList(string: String): List<String> {
    return string.split(",").toList()
}

class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",").toList()
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }

}

fun scheduleNotification(
    context: Context,
    title: String,
    content: String,
    notificationTime: Long,
    everyday: Boolean = false
) {
    val intent = Intent(context, MyNotificationReceiver::class.java).apply {
        putExtra("title", title)
        putExtra("content", content)
        // Add any other extras you need for the notification
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (everyday) alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, notificationTime, AlarmManager.INTERVAL_DAY, pendingIntent
        )
        else alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, notificationTime, pendingIntent
        )
    } else {
        if (everyday) alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP, notificationTime, AlarmManager.INTERVAL_DAY, pendingIntent
            )
        else alarmManager.setExact(
            AlarmManager.RTC_WAKEUP, notificationTime, pendingIntent
        )
    }
}


fun dateToMillis(dateString: String, dateFormat: String): Long {
    return try {
        val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
        val date = sdf.parse(dateString)
        date?.time ?: -1L
    } catch (e: Exception) {
        // Handle the exception if the date parsing fails
        -1L
    }
}