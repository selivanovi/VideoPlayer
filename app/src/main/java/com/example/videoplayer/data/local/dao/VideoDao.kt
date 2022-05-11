package com.example.videoplayer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.videoplayer.data.local.entity.CategoryAndVideos
import com.example.videoplayer.data.local.entity.CategoryEntity
import com.example.videoplayer.data.local.entity.VideoEntity
import kotlinx.coroutines.flow.Flow
import java.nio.charset.CharsetEncoder

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideo(video: VideoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideos(videos: List<VideoEntity>)

    @Transaction
    @Query("SELECT * FROM categoryentity")
    fun getCategoriesWithMovie(): Flow<List<CategoryAndVideos>>

    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Delete
    fun deleteCategories(category: List<CategoryEntity>)

    @Delete
    fun deleteVideos(videos: List<VideoEntity>)

    @Transaction
    fun updateCategoriesAndVideos(categories: List<CategoryEntity>, videos: List<VideoEntity>) {
        deleteCategories(categories)
        deleteVideos(videos)
        insertCategories(categories)
        insertVideos(videos)
    }
}