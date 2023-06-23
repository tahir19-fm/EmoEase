package com.example.emoease.di



import android.content.Context
import androidx.room.Room
import com.example.emoease.roomDb.AppDatabase
import com.example.emoease.roomDb.AppDatabaseDao
import com.example.emoease.networkService.CommonApiService
import com.example.emoease.networkService.OkHttpClientHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesCommonApiService(): CommonApiService {
        return Retrofit.Builder()
            .baseUrl("")
            .client(OkHttpClientHelper().getOkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommonApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase
            = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_db"
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: AppDatabase): AppDatabaseDao
            =noteDatabase.appDao()

}