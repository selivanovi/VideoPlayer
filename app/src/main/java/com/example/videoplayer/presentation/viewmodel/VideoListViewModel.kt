package com.example.videoplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.videoplayer.domain.model.Category
import com.example.videoplayer.domain.repositories.VideoRepository
import com.example.videoplayer.presentation.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoListViewModel(
    private val videoRepository: VideoRepository
) : BaseViewModel() {

    fun getCategories(): Flow<List<Category>> = videoRepository.getCategories()

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val videoRepository: VideoRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VideoListViewModel(videoRepository) as T
        }
    }
}