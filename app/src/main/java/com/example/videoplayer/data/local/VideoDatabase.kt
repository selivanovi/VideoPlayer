package com.example.videoplayer.data.local

import androidx.room.Database
import com.example.videoplayer.data.local.dao.VideoDao
import com.example.videoplayer.data.local.entity.CategoryEntity
import com.example.videoplayer.data.local.entity.VideoEntity

@Database(
    entities = [
        CategoryEntity::class,
        VideoEntity::class
    ],
    version = 1
)
abstract class VideoDatabase {

    abstract val dao: VideoDao

    companion object {
        const val DATABASE_NAME = "video_database"
    }
}