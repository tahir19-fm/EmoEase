package com.example.emoease.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OneTimeServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(activityModal: OneTimeService)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertActivities(activityModal: List<ActivityModal>)

    @Query("SELECT EXISTS(SELECT 1 FROM one_time_tbl)")
    suspend fun exists(): Boolean
}