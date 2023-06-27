package com.example.emoease.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.emoease.utils.ListConverter

@Database(entities = [EmotionModal::class,ActivityModal::class], version = 3, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDatabaseDao
}