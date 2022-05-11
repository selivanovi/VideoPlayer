package com.example.videoplayer.domain.model

import com.example.videoplayer.data.remote.dto.VideoDto

data class Category(
    val name: String,
    val videos: List<VideoDto>
)