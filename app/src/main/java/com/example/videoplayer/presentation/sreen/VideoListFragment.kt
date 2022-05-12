package com.example.videoplayer.presentation.sreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.databinding.FragmentVideoListBinding
import com.example.videoplayer.presentation.BaseFragment
import com.example.videoplayer.presentation.adapter.VideoListAdapter
import com.example.videoplayer.presentation.viewmodel.VideoListViewModel
import com.example.videoplayer.utils.appComponent
import kotlinx.coroutines.flow.onEach


class VideoListFragment : BaseFragment<FragmentVideoListBinding, VideoListViewModel>(
    FragmentVideoListBinding::inflate
) {

    private val videoListAdapter = VideoListAdapter(::onRecyclerViewClickListener)

    override val viewModel: VideoListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.videoRecyclerView) {
            adapter = videoListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getCategories().onEach {
            videoListAdapter.setData(it)
        }
    }

    private fun onRecyclerViewClickListener(videoId: Long) {

    }
}