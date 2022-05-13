package com.example.videoplayer.presentation.viewmodel

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.videoplayer.domain.model.Category
import com.example.videoplayer.domain.model.Video
import com.example.videoplayer.domain.repositories.VideoRepository
import com.example.videoplayer.presentation.BaseViewModel
import com.google.android.exoplayer2.MediaItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel(
    private val videoRepository: VideoRepository
) : BaseViewModel() {

    private val _channelOfVideoList = Channel<List<Video>>()
    val channelOfVideoList = _channelOfVideoList.receiveAsFlow()

    fun handleVideoList() {
        launch {
            val videos = videoRepository.getCategoriesList().flatMap { it.videos }

            _channelOfVideoList.send(videos)
        }
    }

    private fun convertVideoToMediaItem(video: Video): MediaBrowserCompat.MediaItem {
        val descriptionCompat = MediaDescriptionCompat.Builder()
            .setTitle(video.title)
            .setSubtitle(video.subtitle)
            .setDescription(video.description)
            .build()
        return MediaBrowserCompat.MediaItem(
            descriptionCompat,
            MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
        )
    }


    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val videoRepository: VideoRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailsViewModel(videoRepository) as T
        }
    }
}