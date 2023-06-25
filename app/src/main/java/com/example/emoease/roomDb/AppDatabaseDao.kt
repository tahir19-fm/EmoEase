package com.example.emoease.roomDb



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AppDatabaseDao {

    //insert activities in ist launch
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(activityModal: ActivityModal)


    //get full data from emotion table
    @Query("SELECT * from emotion_tbl")
    suspend fun getList():List<EmotionModal>

    //delete emotion table
    @Query("DELETE from emotion_tbl")
    suspend fun deleteAll()

    //save mood all data for one date
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMood(newsModel: EmotionModal)

    //get all emotion details
    @Query("SELECT * from emotion_tbl WHERE id=:id")
    suspend fun getEmotionDetails(id: String):EmotionModal

    //update mood
    @Query("UPDATE emotion_tbl SET mood=:newMood WHERE id=:id")
    suspend fun updateMood(newMood:Int,id: String)

    //get updated mood
    @Query("SELECT mood from emotion_tbl WHERE id=:id")
    suspend fun getMood(id: String):Int

    //update my notes
    @Query("UPDATE emotion_tbl SET myNote=:newNote WHERE id=:id")
    suspend fun updateNotes(newNote:String,id: String)
    //get updated notes
    @Query("SELECT myNote from emotion_tbl WHERE id=:id")
    suspend fun getNotes(id: String):String

    //get all activity list
    @Query("SELECT * from activity_table WHERE activities = :id")
    suspend fun getActivityItems(id:String):ActivityModal

    //add new activity item
    @Query("UPDATE activity_table SET items = :newValue WHERE activities = :itemId")
    suspend fun updateColumnValue(itemId: String, newValue: List<String>)

    //get activity items
    @Query("SELECT :name from emotion_tbl WHERE id=:id")
    suspend fun getSelectedActivity(name: String,id: String):List<String>
}