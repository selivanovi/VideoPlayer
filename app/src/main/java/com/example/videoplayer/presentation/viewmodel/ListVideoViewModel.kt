package com.example.videoplayer.presentation.viewmodel

import com.example.videoplayer.domain.model.Category
import com.example.videoplayer.domain.repositories.VideoRepository
import com.example.videoplayer.presentation.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class ListVideoViewModel(
    private val videoRepository: VideoRepository
) : BaseViewModel() {

    fun getCategories(): Flow<List<Category>>  = videoRepository.getCategories()
}