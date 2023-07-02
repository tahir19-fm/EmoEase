package com.example.emoease.screens.accountScreen.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.example.emoease.screens.accountScreen.util.MyNotificationReceiver
import java.util.Calendar


data class d(val title: String, val content: String, val time: Long)
@Composable
fun AccountScreen() {
    val data= remember {
        mutableStateOf(d("","",0))
    }
    val setNotify= rememberSaveable {
        mutableStateOf(false)
    }
    ScheduleNotificationButton(notificationTime = addSecondsToCurrentTime(5)) { title, content, time ->
//        ScheduleNotification(title, content, time)
            data.value=d(title, content = content,time)
        setNotify.value=true
    }
    if (setNotify.value){
        ScheduleNotification(
            title = data.value.title,
            content = data.value.content,
            notificationTime = data.value.time
        ).apply {
            LaunchedEffect(setNotify){
                setNotify.value=false
            }
        }

    }
}
fun addSecondsToCurrentTime(seconds: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.SECOND, seconds)
    return calendar.timeInMillis
}

@Composable
fun ScheduleNotificationButton(
    notificationTime: Long,
    onScheduleNotification:(title: String, content: String, notificationTime: Long) -> Unit
) {
    Button(
        onClick = {
            onScheduleNotification(
                  "Hello",
                 "Haw are you",
               notificationTime
            )
        }
    ) {
        Text("Schedule Notification")
    }
}

@Composable
fun ScheduleNotification(
    title: String,
    content: String,
    notificationTime: Long
) {
    val context = LocalContext.current

    val intent = Intent(context, MyNotificationReceiver::class.java)
        .apply {
            putExtra("title", title)
            putExtra("content", content)
            // Add any other extras you need for the notification
        }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            notificationTime,
            pendingIntent
        )
    } else {
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            notificationTime,
            pendingIntent
        )
    }
}
