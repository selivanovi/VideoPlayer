package com.example.videoplayer.data.remote.dto

import com.example.videoplayer.data.local.entity.VideoEntity


data class VideoDto(
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
) {
    fun toVideoEntity(category: String): VideoEntity =
        VideoEntity(
            sources = sources.first(),
            categoryName = category,
            description = description,
            subtitle = subtitle,
            thumb = convertThumbToUrl(),
            title = title
        )

    private fun convertThumbToUrl(): String {
        val videoUrl = sources.first()
        val lastIndex = videoUrl.lastIndexOf("/")
        val url = videoUrl.substring(0..lastIndex)
        return url + thumb
    }
}