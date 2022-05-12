package com.example.videoplayer.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videoplayer.R
import com.example.videoplayer.databinding.ListHeaderBinding
import com.example.videoplayer.databinding.ListItemBinding
import com.example.videoplayer.domain.model.Category
import com.example.videoplayer.domain.model.Video

class VideoListAdapter(
    private val onClickVideoListener: (videoId: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = mutableListOf<Any>()

    fun setData(newCategories: List<Category>) {
        list.clear()
        newCategories.forEach { category ->
            list.add(category.name)
            list.addAll(category.videos)
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == HEADER) {
            val bindingHeader = ListHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return HeaderViewHolder(bindingHeader)
        } else {
            val bindingVideo = ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return VideoViewHolder(bindingVideo)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HEADER) {
            (holder as HeaderViewHolder).bind(list[position] as String)
        } else {
            val video = list[position] as Video
            (holder as VideoViewHolder).bind(video)
            holder.itemView.setOnClickListener {
                onClickVideoListener(video.sources)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class VideoViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            Log.d("Video adapter", video.toString())
            binding.videoTitle.text = video.title
            Glide.with(binding.root)
                .asBitmap()
                .load(video.thumb)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(binding.videoImage)
        }
    }

    class HeaderViewHolder(
        private val binding: ListHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(header: String) {
            binding.headerTitle.text = header
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (list[position] is String) {
            HEADER
        } else {
            VIDEO_ITEM
        }


    companion object {
        const val HEADER = 1
        const val VIDEO_ITEM = 2
    }
}