package com.example.videoplayer.data

import com.example.videoplayer.data.local.dao.VideoDao
import com.example.videoplayer.data.local.entity.CategoryEntity
import com.example.videoplayer.data.local.entity.VideoEntity
import com.example.videoplayer.data.remote.service.VideoService
import com.example.videoplayer.domain.model.Category
import com.example.videoplayer.domain.repositories.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoRepositoryImpl(
    private val networkService: VideoService,
    private val dao: VideoDao
) : VideoRepository {

    override suspend fun getCategories(): Flow<List<Category>> = flow {
        val localCategories = dao.getCategoriesWithMovie().map { categoryAndVideos ->
            categoryAndVideos.toCategory()
        }
        emit(localCategories)

        val networkCategories = networkService.getCategories()
        if (networkCategories.isNotEmpty()) {
            val categories = mutableListOf<CategoryEntity>()
            val videos = mutableListOf<VideoEntity>()

            networkCategories.forEach { categoryDto ->
                val categoryEntity = categoryDto.toCategoryEntity()

                categories.add(categoryEntity)
                categoryEntity.categoryId?.let { categoryId ->
                    videos.addAll(
                        categoryDto.videos.map { it.toVideoEntity(categoryId) }
                    )
                }
            }

            dao.updateCategoriesAndVideos(categories, videos)
        }

        val mewLocalCategories = dao.getCategoriesWithMovie().map { categoryAndVideos ->
            categoryAndVideos.toCategory()
        }
        emit(mewLocalCategories)
    }

}