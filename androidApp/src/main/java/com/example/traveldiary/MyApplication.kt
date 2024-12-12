package com.example.traveldiary

import android.app.Application
import com.example.traveldiary.android.di.setupKoin
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeDependencies()
        FirebaseApp.initializeApp(this)
    }

    private fun initializeDependencies() {
        setupKoin(this)
    }
}