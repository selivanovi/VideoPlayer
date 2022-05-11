package com.example.videoplayer.domain.repositories

import com.example.videoplayer.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    suspend fun getCategories(): Flow<List<Category>>
}