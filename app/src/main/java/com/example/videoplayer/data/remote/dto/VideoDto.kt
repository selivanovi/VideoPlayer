package com.example.videoplayer.data.remote.dto

import com.example.videoplayer.data.local.entity.VideoEntity

data class VideoDto(
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
) {
    fun toVideoEntity(): VideoEntity =
        VideoEntity(
            description = description,
            sources = sources.first(),
            subtitle = subtitle,
            thumb = thumb,
            title = title
        )
}