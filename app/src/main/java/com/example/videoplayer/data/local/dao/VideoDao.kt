package com.example.videoplayer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.videoplayer.data.local.entity.CategoryAndVideos
import com.example.videoplayer.data.local.entity.CategoryEntity
import com.example.videoplayer.data.local.entity.VideoEntity

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: VideoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<VideoEntity>)

    @Transaction
    @Query("SELECT * FROM categoryentity")
    suspend fun getCategoriesWithMovie(): List<CategoryAndVideos>

    @Query("DELETE FROM categoryentity")
    suspend fun deleteAllCategories()


    @Query("DELETE FROM videoentity")
    suspend fun deleteAllVideos()

    @Transaction
    suspend fun updateCategoriesAndVideos(
        categories: List<CategoryEntity>,
        videos: List<VideoEntity>
    ) {
        deleteAllCategories()
        deleteAllVideos()
        insertCategories(categories)
        insertVideos(videos)
    }
}