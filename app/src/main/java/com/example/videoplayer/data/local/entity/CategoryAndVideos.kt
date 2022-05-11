package com.example.videoplayer.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryAndVideos(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val videos: List<VideoEntity>
)