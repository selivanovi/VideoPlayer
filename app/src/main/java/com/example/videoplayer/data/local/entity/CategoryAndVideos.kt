package com.example.videoplayer.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.videoplayer.domain.model.Category

data class CategoryAndVideos(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "categoryName"
    )
    val videos: List<VideoEntity>
) {
    fun toCategory(): Category =
        Category(
            name = category.name,
            videos = videos.map { it.toVideo() }
        )
}