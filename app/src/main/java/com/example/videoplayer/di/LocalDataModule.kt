package com.example.videoplayer.di

import android.content.Context
import androidx.room.Room
import com.example.videoplayer.data.local.VideoDatabase
import com.example.videoplayer.data.local.dao.VideoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun provideVideoDao(videoDatabase: VideoDatabase) : VideoDao =
        videoDatabase.dao

    @Provides
    @Singleton
    fun provideVideoDatabase(context: Context): VideoDatabase =
        Room.databaseBuilder(
            context,
            VideoDatabase::class.java,
            VideoDatabase.DATABASE_NAME
        ).build()
}
