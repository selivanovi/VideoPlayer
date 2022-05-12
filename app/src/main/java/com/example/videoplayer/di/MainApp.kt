package com.example.videoplayer.di

import android.app.Application

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().setContext(this).build()
    }
}