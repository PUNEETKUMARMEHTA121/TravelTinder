package com.example.traveldiary.android.di

import com.example.traveldiary.android.common.base.BaseSharedPreference
import org.koin.dsl.module

val AppModule = module {
    single<BaseSharedPreference> { BaseSharedPreference(get()) }
}