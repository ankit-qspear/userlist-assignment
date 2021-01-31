package com.example.assignmentuserlist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    companion object{
        const val BASE_URL = "http://www.mocky.io/"
    }
}