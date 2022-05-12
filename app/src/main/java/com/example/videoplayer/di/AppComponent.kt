package com.example.videoplayer.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.videoplayer.presentation.BaseFragment
import com.example.videoplayer.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        LocalDataModule::class,
        BindsModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(fragment: Fragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context) : Builder

        fun build() : AppComponent
    }
}