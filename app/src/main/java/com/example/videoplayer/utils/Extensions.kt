package com.example.videoplayer.utils

import android.content.Context
import com.example.videoplayer.di.AppComponent
import com.example.videoplayer.di.MainApp

val Context.appComponent: AppComponent
    get() = when(this) {
        is MainApp -> appComponent
        else ->this.applicationContext.appComponent
    }