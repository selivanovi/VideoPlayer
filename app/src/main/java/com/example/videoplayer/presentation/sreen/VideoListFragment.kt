package com.example.videoplayer.presentation.sreen

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.R
import com.example.videoplayer.databinding.FragmentVideoListBinding
import com.example.videoplayer.presentation.BaseFragment
import com.example.videoplayer.presentation.adapter.VideoListAdapter
import com.example.videoplayer.presentation.state.VideoListState
import com.example.videoplayer.presentation.viewmodel.VideoListViewModel
import com.example.videoplayer.utils.appComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class VideoListFragment : BaseFragment<FragmentVideoListBinding, VideoListViewModel>(
    FragmentVideoListBinding::inflate
) {

    private val videoListAdapter = VideoListAdapter(::onRecyclerViewClickListener)

    @Inject
    lateinit var listViewModelFactory: VideoListViewModel.Factory

    override val viewModel: VideoListViewModel by viewModels { listViewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireContext().appComponent.inject(this)

        with(binding.videoRecyclerView) {
            adapter = videoListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        viewModel.channelOfState.onEach { state ->
            when (state) {
                VideoListState.Loading -> {
                    with(binding) {
                        progressBar.visibility = View.VISIBLE
                        errorImage.visibility = View.GONE
                        errorText.visibility = View.GONE
                        videoRecyclerView.visibility = View.GONE
                    }
                }
                is VideoListState.VideoListData -> {
                    binding.videoRecyclerView.visibility = View.VISIBLE
                    videoListAdapter.setData(state.list)
                    with(binding) {
                        progressBar.visibility = View.GONE
                        errorImage.visibility = View.GONE
                        errorText.visibility = View.GONE
                    }
                }
                is VideoListState.GenericsError -> {
                    setVisibleForErrorViews()
                    with(binding) {
                        errorImage.setImageResource(R.drawable.ic_baseline_cloud_off_24)
                        errorText.text =
                            resources.getString(
                                R.string.generics_error_message,
                                state.code.toString()
                            )
                    }
                }
                VideoListState.UnknownError -> {
                    setVisibleForErrorViews()
                    setError(R.drawable.ic_baseline_error_24, R.string.unknown_error_message)
                }
                VideoListState.NetworkError -> {
                    setVisibleForErrorViews()
                    setError(R.drawable.ic_baseline_cloud_off_24, R.string.network_error_message)
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.handleCategories()
    }

    private fun setVisibleForErrorViews() {
        with(binding) {
            progressBar.visibility = View.GONE
            errorImage.visibility = View.VISIBLE
            errorText.visibility = View.VISIBLE
            videoRecyclerView.visibility = View.GONE
        }
    }

    private fun setError(@DrawableRes drawable: Int, @StringRes string: Int) {
        with(binding) {
            errorImage.setImageResource(drawable)
            errorText.text = resources.getString(string)
        }
    }

    private fun onRecyclerViewClickListener(videoId: String) {

    }
}