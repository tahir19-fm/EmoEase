package com.example.emoease.roomDb.modals

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_table")
data class ActivityModal(
    @PrimaryKey
    @ColumnInfo(name = "activities")
    val id:String,
    @ColumnInfo(name = "items")
    val items:List<String>
)
