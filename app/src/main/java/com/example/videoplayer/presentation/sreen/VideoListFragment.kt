package com.example.videoplayer.presentation.sreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.databinding.FragmentVideoListBinding
import com.example.videoplayer.di.MainApp
import com.example.videoplayer.presentation.BaseFragment
import com.example.videoplayer.presentation.adapter.VideoListAdapter
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

    override val viewModel: VideoListViewModel by viewModels{ listViewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireContext().appComponent.inject(this)

        with(binding.videoRecyclerView) {
            adapter = videoListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getCategories().onEach {
            Log.d("MainActivity", it.toString())
            videoListAdapter.setData(it)
        }.launchIn(lifecycleScope)
    }

    private fun onRecyclerViewClickListener(videoId: String) {

    }
}