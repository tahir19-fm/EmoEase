package com.example.emoease.screens.moodTrackingScreen.util

import androidx.room.TypeConverter
import com.example.emoease.R
import com.example.emoease.screens.moodTrackingScreen.data.MoodItem
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



 fun todayDate(): String {
    val currentDate = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    return dateFormat.format(currentDate)
}
fun getActivityId(name:String):String{
    when(name){
        Constants.Activities->{
            return "activities"
        }
        Constants.Social->{
            return "social"
        }
        Constants.Sleep->{
            return "sleep"
        }
        Constants.Symptoms->{
            return "symptoms"
        }
    }
    return ""
}

val listOfMoods = listOf(
    MoodItem("happy", R.raw.happy_emoji),
    MoodItem("very happy", R.raw.very_happy_emojji),
    MoodItem("neutral", R.raw.neutral_emoji),
    MoodItem("sad", R.raw.sad_emoji),
    MoodItem("sad and tired", R.raw.very_sad_emoji),
    MoodItem("angry", R.raw.angry_emoji),
    MoodItem("very angry", R.raw.very_angry_emoji),
)


