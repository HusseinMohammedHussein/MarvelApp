package com.husseinmohammed.marvelapp

import android.app.Application
import android.content.Context
import timber.log.Timber


// Created by Hussein Mohammed on 9/13/2021.
class MarvelApplication : Application() {
    private var timberTree = Timber.DebugTree()
    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        if (BuildConfig.DEBUG)
            Timber.plant(timberTree)
    }
}