package com.photos.picsum

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PicsumApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
    }
}