package com.example.videoplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val videoId: Long? = null,
    val categoryId: Long,
    val description: String,
    val sources: String,
    val subtitle: String,
    val thumb: String,
    val title: String
) {

}