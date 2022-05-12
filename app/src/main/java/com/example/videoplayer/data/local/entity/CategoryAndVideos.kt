package com.example.videoplayer.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.videoplayer.domain.model.Category

data class CategoryAndVideos(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val videos: List<VideoEntity>
) {
    fun toCategory(): Category =
        Category(
            categoryId = category.categoryId,
            name = category.name,
            videos = videos.map { it.toVideo() }
        )
}