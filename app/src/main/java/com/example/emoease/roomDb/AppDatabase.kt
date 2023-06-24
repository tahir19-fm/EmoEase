package com.example.emoease.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.emoease.screens.moodTrackingScreen.util.ListConverter

@Database(entities = [EmotionModal::class], version = 2, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDatabaseDao
}