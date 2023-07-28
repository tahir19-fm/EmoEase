package com.example.emoease.roomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "one_time_tbl")
class OneTimeService (
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String="N/A",
    @ColumnInfo(name = "exists")
    val exists:Boolean
    )