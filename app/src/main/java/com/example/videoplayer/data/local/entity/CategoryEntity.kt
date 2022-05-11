package com.example.videoplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long? = null,
    val name: String,
)
