package com.example.videoplayer.domain.model


data class Category(
    val categoryId: Long,
    val name: String,
    val videos: List<Video>
)