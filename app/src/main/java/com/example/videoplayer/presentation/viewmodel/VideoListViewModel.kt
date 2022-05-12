package com.example.videoplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.domain.repositories.VideoRepository
import com.example.videoplayer.presentation.BaseViewModel
import com.example.videoplayer.presentation.state.VideoListState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class VideoListViewModel(
    private val videoRepository: VideoRepository
) : BaseViewModel() {

    private val _channelOfState = Channel<VideoListState>()
    val channelOfState = _channelOfState.receiveAsFlow()

    fun handleCategories() {
        launch {
            _channelOfState.send(VideoListState.Loading)
        }
        videoRepository.getCategories().onEach { result ->
            result.onSuccess {  list ->
                if (list.isNotEmpty()) {
                    _channelOfState.send(VideoListState.VideoListData(list))
                }
            }
            result.onFailure { throwable ->
                when(throwable) {
                    is IOException -> _channelOfState.send(VideoListState.NetworkError)
                    is HttpException -> _channelOfState.send(VideoListState.GenericsError(throwable.code()))
                    else -> _channelOfState.send(VideoListState.UnknownError)
                }
            }
        }.launchIn(viewModelScope)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val videoRepository: VideoRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VideoListViewModel(videoRepository) as T
        }
    }
}