package com.beardness.macosmsapp.application

import android.app.Application
import com.beardness.macosmsapp.usecase.startup.StartUpProtocol
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MacoSmsApp : Application() {

    @Inject
    lateinit var startUp: StartUpProtocol

    override fun onCreate() {
        super.onCreate()
        startup()
    }

    private fun startup() {
        startUp.onStartUp()
    }
}