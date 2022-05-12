package com.example.videoplayer.presentation.state

import com.example.videoplayer.domain.model.Category
import okhttp3.internal.http2.ErrorCode

sealed interface VideoListState {

    object Loading : VideoListState
    data class GenericsError(val code: Int) : VideoListState
    data class VideoListData(val list: List<Category>) : VideoListState
    object NetworkError : VideoListState
    object UnknownError : VideoListState
}