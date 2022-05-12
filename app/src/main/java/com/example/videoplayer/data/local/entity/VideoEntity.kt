package com.example.videoplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.videoplayer.domain.model.Video

@Entity
data class VideoEntity(
    @PrimaryKey
    val sources: String,
    val categoryName: String,
    val description: String,
    val subtitle: String,
    val thumb: String,
    val title: String
) {
    fun toVideo(): Video =
        Video(
            description = description,
            sources = sources,
            subtitle = subtitle,
            thumb = thumb,
            title = title
        )
}