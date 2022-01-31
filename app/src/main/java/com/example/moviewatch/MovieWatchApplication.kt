package com.example.moviewatch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieWatchApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}