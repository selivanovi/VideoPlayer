package com.example.videoplayer.data.remote.dto

import com.example.videoplayer.data.local.entity.CategoryEntity
import com.example.videoplayer.data.local.entity.VideoEntity

data class CategoryDto(
    val name: String,
    val videos: List<VideoDto>
) {
    fun toVideoEntity(): CategoryEntity =
        CategoryEntity(
            name = name,
            videos = videos.map { it.toVideoEntity() }
        )
}