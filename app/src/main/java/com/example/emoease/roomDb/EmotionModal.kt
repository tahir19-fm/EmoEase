package com.example.emoease.roomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "emotion_tbl")
data class EmotionModal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id:String,
    @ColumnInfo(name = "mood")
    var mood:Int,
    @ColumnInfo(name = "activities")
    var activities:List<String>,
    @ColumnInfo(name = "social")
    var social:List<String>,
    @ColumnInfo(name = "sleep")
    var sleep:List<String>,
    @ColumnInfo(name = "symptoms")
    var symptoms:List<String>,
    @ColumnInfo(name = "myNote")
    var myNote:String
)