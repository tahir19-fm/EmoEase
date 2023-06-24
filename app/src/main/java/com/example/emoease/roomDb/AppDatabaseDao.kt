package com.example.emoease.roomDb



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AppDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(list:List<EmotionModal>)


    @Query("SELECT * from emotion_tbl")
    suspend fun getList():List<EmotionModal>

    @Query("DELETE from emotion_tbl")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMood(newsModel: EmotionModal)


}