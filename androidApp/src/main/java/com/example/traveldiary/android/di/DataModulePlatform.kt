package com.example.traveldiary.android.di

import com.example.traveldiary.data.repository.LoginDataSourceRepositoryImpl
import com.example.traveldiary.data.repository.RegisterDataSourceRepositoryImpl
import com.example.traveldiary.domain.repository.LoginDataSourceRepository
import com.example.traveldiary.domain.repository.RegisterDataSourceRepository
import org.koin.dsl.module

val DataModulePlatform = module {

    //TODO  shi jgh rkhna hai file ko
//    single<FirebaseEventDataSourceRepository> {
//        FirebaseEventDataSourceImpl()
//    }

    single<LoginDataSourceRepository> {
        LoginDataSourceRepositoryImpl(get())
    }
    single<RegisterDataSourceRepository> {
        RegisterDataSourceRepositoryImpl(get())
    }
}
