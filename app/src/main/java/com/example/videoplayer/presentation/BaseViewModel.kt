package com.example.videoplayer.presentation

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override val coroutineContext: CoroutineContext =
        viewModelScope.coroutineContext + Dispatchers.IO +coroutineExceptionHandler


    protected abstract fun handleError(throwable: Throwable)
}