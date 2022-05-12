package com.example.videoplayer.di

import com.example.videoplayer.data.VideoRepositoryImpl
import com.example.videoplayer.data.local.VideoDatabase
import com.example.videoplayer.data.remote.service.VideoService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideVideoRepositoryImpl(
        videoService: VideoService,
        videoDatabase: VideoDatabase
    ): VideoRepositoryImpl =
        VideoRepositoryImpl(networkService = videoService, dao = videoDatabase.dao)
}