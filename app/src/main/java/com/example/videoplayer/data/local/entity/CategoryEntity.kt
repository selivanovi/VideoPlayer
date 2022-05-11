package com.example.videoplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.videoplayer.data.remote.dto.VideoDto
import com.example.videoplayer.domain.model.Category
import com.example.videoplayer.domain.model.Video

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long? = null,
    val name: String,
    val videos: List<VideoEntity>
) {
    fun toCategory(): Category =
        Category(
            name = name,
            videos = videos.map { it.toVideo() }
        )
}
