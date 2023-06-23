package com.example.emoease.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDatabaseDao
}