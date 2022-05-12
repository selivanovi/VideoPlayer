package com.example.videoplayer.di

import com.example.videoplayer.data.remote.service.VideoService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideVideoService() : VideoService =
        Retrofit.Builder()
            .baseUrl(VideoService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VideoService::class.java)
}
