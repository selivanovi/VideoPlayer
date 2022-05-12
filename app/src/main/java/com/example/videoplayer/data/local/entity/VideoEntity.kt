package com.example.videoplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.videoplayer.domain.model.Video

@Entity
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val videoId: Long = 0,
    val categoryId: Long,
    val description: String,
    val sources: String,
    val subtitle: String,
    val thumb: String,
    val title: String
) {
    fun toVideo() : Video =
        Video(
            videoId = videoId,
            description = description,
            sources = sources,
            subtitle = subtitle,
            thumb = thumb,
            title = title
        )
}