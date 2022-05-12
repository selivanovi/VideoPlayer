package com.example.videoplayer.di

import com.example.videoplayer.data.VideoRepositoryImpl
import com.example.videoplayer.domain.repositories.VideoRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {

    @Binds
    fun bindVideoRepoImplToVideoRepo(videoRepositoryImpl: VideoRepositoryImpl) : VideoRepository
}
