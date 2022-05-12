package com.example.videoplayer.data

import android.util.Log
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

    override fun getCategories(): Flow<List<Category>> = flow {
        val localCategories = dao.getCategoriesWithMovie().map { categoryAndVideos ->
            categoryAndVideos.toCategory()
        }
        Log.d("VideoRepository old:", localCategories.toString())
        emit(localCategories)

        refreshData()

        val mewLocalCategories = dao.getCategoriesWithMovie().map { categoryAndVideos ->
            categoryAndVideos.toCategory()
        }
        Log.d("VideoRepository new: ", mewLocalCategories.toString())
        emit(mewLocalCategories)
    }

    private suspend fun refreshData() {
        val networkCategories = networkService.getCategories().categories

        if (networkCategories.isNotEmpty()) {
            val categories = mutableListOf<CategoryEntity>()
            val videos = mutableListOf<VideoEntity>()

            networkCategories.forEach { categoryDto ->
                val categoryEntity = categoryDto.toCategoryEntity()

                categories.add(categoryEntity)

                val videosEntities =
                    categoryDto.videos.map { it.toVideoEntity(categoryEntity.name) }
                videos.addAll(videosEntities)
            }
            dao.updateCategoriesAndVideos(categories, videos)
        }
    }

}