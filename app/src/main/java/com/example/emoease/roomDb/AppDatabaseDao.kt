package com.example.emoease.roomDb



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.emoease.roomDb.NewsModel


@Dao
interface AppDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(list:List<NewsModel>)


    @Query("SELECT * from app_tbl")
    suspend fun getList():List<NewsModel>

    @Query("DELETE from app_tbl")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(newsModel: NewsModel)


}