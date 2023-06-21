package com.example.emoease.screens.moodTrackingScreen.data

data class MoodEntry(
    val rating: Int,
    val notes: String,
    val tags: List<String>,
    val timestamp: Long
)
data class MoodItem(val position: Int, val drawableRes: Int)