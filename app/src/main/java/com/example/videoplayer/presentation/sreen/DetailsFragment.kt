package com.example.videoplayer.presentation.sreen

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.videoplayer.databinding.FragmentDetailsBinding
import com.example.videoplayer.presentation.BaseFragment
import com.example.videoplayer.presentation.viewmodel.DetailsViewModel
import com.example.videoplayer.utils.appComponent
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel>(
    FragmentDetailsBinding::inflate
) {

    private var videoUrl: String? = null
    private var player: Player? = null
    private var currentPosition: Int = 0
    private var seekTime: Long = 0

    @Inject
    lateinit var factory: DetailsViewModel.Factory

    override val viewModel: DetailsViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        videoUrl = requireArguments().getString(VIDEO_URL)
            ?: throw IllegalArgumentException("You miss argument videoUrl")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireContext().appComponent.inject(this)

        setupExoPlayer()
        setDataToExoplayer()

        if (savedInstanceState != null) {
            if (savedInstanceState.getInt(MEDIA_ITEM_KEY) != 0) {
                currentPosition = savedInstanceState.getInt(MEDIA_ITEM_KEY)
                seekTime = savedInstanceState.getLong(SEEK_TIME_KEY)
                player?.play()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        player?.seekTo(currentPosition, seekTime)
        Log.d("DetailsFragment onStart", currentPosition.toString())
    }

    override fun onStop() {
        super.onStop()
        player?.release()
    }

    private fun setDataToExoplayer() {
        viewModel.channelOfVideoList.onEach { list ->
            player?.addMediaItems(list.map { video -> MediaItem.fromUri(video.sources) })
            player?.prepare()
            currentPosition = list.map { it.sources }.indexOf(videoUrl)
            player?.seekTo(currentPosition, seekTime)
        }.launchIn(lifecycleScope)
        viewModel.handleVideoList()
    }

    private fun setupExoPlayer() {
        player = ExoPlayer.Builder(requireContext())
            .build()
        player?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Player.STATE_READY -> {
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {

                binding.title.text =
                    mediaMetadata.title ?: mediaMetadata.displayTitle ?: "no title found"
                binding.title.setTextColor(Color.WHITE)
                binding.title
            }
        })
        player?.let {
            binding.videoView.player = player
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        player?.let {
            outState.putLong(SEEK_TIME_KEY, it.currentPosition)
            outState.putInt(MEDIA_ITEM_KEY, it.currentMediaItemIndex)
        }

    }

    companion object {
        const val VIDEO_URL = "video_url"
        const val SEEK_TIME_KEY = "seek_time"
        const val MEDIA_ITEM_KEY = "media_item"

        @JvmStatic
        fun newInstance(videoUrl: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(VIDEO_URL, videoUrl)
                }
            }
    }
}