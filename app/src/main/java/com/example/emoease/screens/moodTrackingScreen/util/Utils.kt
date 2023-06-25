package com.example.emoease.screens.moodTrackingScreen.util

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

val listOfActivities = listOf(
    "⚽️Football",
    "🏏Cricket",
    "🏀Basketball",
    "🏑Hockey",
    "🎾Tennis",
    "🏐Volleyball",
    "🏓Table Tennis",
    "⚾️Baseball",
    "🏉Rugby"
)
val listOfSocial= listOf(
    "👨‍👩‍👧Family",
    "😊Date",
    "🎉Party",
    "🍻Chill",
    "🚻Friends",
    "🏫School"
)
val listOfSleep= listOf(
    "🛏️Early to bed",
    "🛏️Late to bed",
    "😔Bad sleep",
    "😀Good sleep",
    "🕐Early awake",
    "🕝Late awake"
)

class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }
}

 fun todayDate(): String {
    val currentDate = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    return dateFormat.format(currentDate)
}