package com.example.videoplayer.data.remote.dto

import com.example.videoplayer.data.local.entity.VideoEntity


data class VideoDto(
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
) {
    fun toVideoEntity(categoryId: Long): VideoEntity =
        VideoEntity(
            categoryId = categoryId,
            description = description,
            sources = sources.first(),
            subtitle = subtitle,
            thumb = convertThumbToUrl(),
            title = title
        )

    private fun convertThumbToUrl(): String {
        val videoUrl = sources.first()
        val lastIndex = videoUrl.lastIndexOf("/")
        val url = videoUrl.substring(0..lastIndex + 1)
        return url + thumb
    }
}