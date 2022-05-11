package com.example.videoplayer.presentation

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val _channelOfError = Channel<Throwable>()
    val channelError = _channelOfError.receiveAsFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override val coroutineContext: CoroutineContext =
        viewModelScope.coroutineContext + Dispatchers.IO +coroutineExceptionHandler


    @CallSuper
    protected fun handleError(throwable: Throwable) {
        launch {
            _channelOfError.send(throwable)
        }
    }
}