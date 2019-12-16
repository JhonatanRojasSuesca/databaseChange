package com.example.database

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        private lateinit var appContext: Context
        fun getAppContext(): Context = appContext
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}