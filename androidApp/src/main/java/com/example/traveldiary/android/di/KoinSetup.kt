package com.example.traveldiary.android.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun setupKoin(application: Application) = startKoin {
    androidLogger()
    androidContext(application)
    modules(
//        RemoteModule, //ktor
//        DomainModule,//others
//        DomainModulePlatform,//use cases
//        PresentationModule,//view models
//        AndroidRepo, //ex: location repos
//        SharedPrefModule //sharedpref

        PresentationModule, //view models
        DomainModulePlatform,//ktor
        DataModulePlatform,//firebase
        AppModule,//SharedPreference

    )
}