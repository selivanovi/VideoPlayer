package com.example.videoplayer.domain.repositories

import com.example.videoplayer.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    fun getCategoriesFlow(): Flow<Result<List<Category>>>

    suspend fun getCategoriesList(): List<Category>

}