package com.example.videoplayer.data.remote.dto

import com.example.videoplayer.data.local.entity.CategoryEntity

data class CategoryDto(
    val name: String,
    val videos: List<VideoDto>
) {
    fun toCategoryEntity(): CategoryEntity =
        CategoryEntity(
            name = name
        )
}