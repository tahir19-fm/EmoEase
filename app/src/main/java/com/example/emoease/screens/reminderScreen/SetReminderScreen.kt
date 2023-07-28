package com.example.emoease.screens.reminderScreen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.emoease.utils.scheduleNotification
import java.util.Calendar

data class d(val title:String, val content:String, var time:Long)
@Composable
fun SetReminderScreen() {
    val data= remember {
        mutableStateOf(d("","",0))
    }
    val setNotify= rememberSaveable {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        val context=LocalContext.current
        ScheduleNotificationButton(notificationTime = addSecondsToCurrentTime(5)) { title, content, time ->
//        ScheduleNotification(title, content, time)
            data.value = d(title, content = content, time)
            scheduleNotification(context = context,title,content, notificationTime = time)
        }
        DayAndTimeSelectionScreen()
        RequestNotificationPermissionButton(onPermissionResult = {})
    }
//    if (setNotify.value){
//        ScheduleNotification(
//            title = data.value.title,
//            content = data.value.content,
//            notificationTime = data.value.time,
//            LocalContext.current
//        ).apply {
//            LaunchedEffect(setNotify){
//                setNotify.value=false
//            }
//        }
//    }
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
fun RequestNotificationPermissionButton(onPermissionResult: (Boolean) -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // For Android 8.0 (Oreo) and above, use the system notification settings screen
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                context.startActivity(intent)
            } else {
                // For Android versions below 8.0, use the standard permission request
                val notificationPermission = Manifest.permission.VIBRATE
                val requestCode = 1
                val permissionResult = ContextCompat.checkSelfPermission(context, notificationPermission)
                if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                    // Permission already granted
                    onPermissionResult(true)
                } else {
                    // Request permission
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(notificationPermission),
                        requestCode
                    )
                }
            }
        }
    ) {
        Text("Allow Notifications")
    }
}


@Composable
fun DayAndTimeSelectionScreen() {
    var selectedDay by remember { mutableStateOf(DayOfWeek.MONDAY) }
    var selectedTime by remember { mutableStateOf("12:00 AM") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Day of the Week selection
        Text(
            text = "Select Day of the Week:",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        DayOfWeekSelection(
            selectedDay = selectedDay,
            onDaySelected = { day -> selectedDay = day }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Time selection
        Text(
            text = "Select Time:",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
//        TimePicker(
//            selectedTime = selectedTime,
//            onTimeSelected = { time -> selectedTime = time }
//        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display selected day and time
        Text(
            text = "Selected Day: ${selectedDay.name}",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Selected Time: $selectedTime",
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}

@Composable
fun DayOfWeekSelection(
    selectedDay: DayOfWeek,
    onDaySelected: (DayOfWeek) -> Unit
) {
    val daysOfWeek = listOf(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY
    )

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (day in daysOfWeek) {
            DayButton(
                day = day,
                isSelected = selectedDay == day,
                onDaySelected = { onDaySelected(day) }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DayButton(
    day: DayOfWeek,
    isSelected: Boolean,
    onDaySelected: () -> Unit
) {
    Surface(
        color = if (isSelected) MaterialTheme.colors.primary else Color.Gray,
        onClick = { onDaySelected() }
    ) {
        Text(
            text = day.name,
            fontSize = 16.sp,
            color = if (isSelected) Color.White else Color.Black,
            modifier = Modifier.padding(8.dp)
        )
    }
}

//@Composable
//fun TimePicker(
//    selectedTime: String,
//    onTimeSelected: (String) -> Unit
//) {
//    // Regular expressions to match the time format "hh:mm a"
//    val hoursRegex = Regex("([0-1]?[0-9]|2[0-3])")
//    val minutesRegex = Regex("([0-5][0-9])")
//    val amPmRegex = Regex("(AM|PM)")
//
//    // Split the selected time into hours, minutes, and AM/PM
//    val (hours, minutes, amPm) = selectedTime.split(":|\\s".toRegex())
//
//    // State to hold the current time input for hours and minutes
//    var hoursTextFieldValue by remember { mutableStateOf(TextFieldValue(hours)) }
//    var minutesTextFieldValue by remember { mutableStateOf(TextFieldValue(minutes)) }
//
//    // Function to validate and update the time input
//    fun updateTimeInput(newHours: String, newMinutes: String) {
//        // Check if the new input is valid
//        if (newHours.matches(hoursRegex) && newMinutes.matches(minutesRegex)) {
//            val newTime = String.format("%02d:%02d %s", newHours.toInt(), newMinutes.toInt(), amPm)
//            hoursTextFieldValue = TextFieldValue(newHours)
//            minutesTextFieldValue = TextFieldValue(newMinutes)
//            onTimeSelected(newTime)
//        }
//    }
//
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.Center
//    ) {
//        // Hours input field
//        BasicTextField(
//            value = hoursTextFieldValue,
//            onValueChange = { newInput ->
//                hoursTextFieldValue = newInput
//                updateTimeInput(newInput.text, minutesTextFieldValue.text)
//            },
//            textStyle = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
//            singleLine = true,
//            modifier = Modifier
//                .weight(1f)
//                .padding(8.dp),
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number,
//                imeAction = ImeAction.Done
//            )
//        )
//
//        Text(text = ":", modifier = Modifier.padding(8.dp))
//
//        // Minutes input field
//        BasicTextField(
//            value = minutesTextFieldValue,
//            onValueChange = { newInput ->
//                minutesTextFieldValue = newInput
//                updateTimeInput(hoursTextFieldValue.text, newInput.text)
//            },
//            textStyle = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
//            singleLine = true,
//            modifier = Modifier
//                .weight(1f)
//                .padding(8.dp),
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number,
//                imeAction = ImeAction.Done
//            )
//        )
//    }
//}


enum class DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}


