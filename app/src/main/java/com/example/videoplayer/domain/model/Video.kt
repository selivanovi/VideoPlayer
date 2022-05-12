package com.example.videoplayer.domain.model

data class Video(
    val videoId: Long,
    val description: String,
    val sources: String,
    val subtitle: String,
    val thumb: String,
    val title: String
)