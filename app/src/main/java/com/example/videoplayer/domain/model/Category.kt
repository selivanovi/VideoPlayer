package com.example.videoplayer.domain.model


data class Category(
    val name: String,
    val videos: List<Video>
)