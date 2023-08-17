package com.example.emoease.roomDb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.emoease.roomDb.modals.ActivityModal
import com.example.emoease.roomDb.modals.OneTimeService

@Dao
interface OneTimeServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(activityModal: OneTimeService)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertActivities(activityModal: List<ActivityModal>)

    @Query("SELECT EXISTS(SELECT 1 FROM one_time_tbl)")
    suspend fun exists(): Boolean
}