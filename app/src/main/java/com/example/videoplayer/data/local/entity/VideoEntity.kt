package com.example.videoplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.videoplayer.domain.model.Video

@Entity
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val videoId: Long? = null,
    val description: String,
    val sources: String,
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