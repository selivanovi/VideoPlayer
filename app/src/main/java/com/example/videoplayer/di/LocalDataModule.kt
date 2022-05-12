package com.example.videoplayer.di

import android.content.Context
import androidx.room.Room
import com.example.videoplayer.data.local.VideoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun provideVideoDatabase(context: Context): VideoDatabase =
        Room.databaseBuilder(
            context,
            VideoDatabase::class.java,
            VideoDatabase.DATABASE_NAME
        ).build()
}
