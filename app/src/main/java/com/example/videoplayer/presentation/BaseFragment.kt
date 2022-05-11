package com.example.videoplayer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.videoplayer.utils.Inflate

abstract class BaseFragment<VB: ViewBinding, VM : BaseViewModel>(
    val inflate: Inflate<VB>
) : Fragment() {
    private var _binding: VB? = null

    protected val binding
        get() = _binding!!

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container, false)
        return binding.root
    }
}