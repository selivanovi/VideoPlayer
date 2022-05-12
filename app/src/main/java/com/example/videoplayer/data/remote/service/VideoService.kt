package com.example.videoplayer.data.remote.service

import com.example.videoplayer.data.remote.dto.CategoriesDto
import com.example.videoplayer.data.remote.dto.CategoryDto
import retrofit2.http.GET

interface VideoService {

    @GET("videos")
    suspend fun getCategories(): CategoriesDto

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/DavidStdn/NitrixTestTask/main/"
    }
}