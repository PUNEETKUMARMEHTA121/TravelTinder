package com.example.traveldiary.android.di

import com.example.traveldiary.android.logout.SignOutViewModel
import com.example.traveldiary.android.loginregisterauth.LoginViewModel
import com.example.traveldiary.android.loginregisterauth.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {
    //TODO  shi jgh rkhna hai file ko
    viewModel {
        LoginViewModel(get(), get(), get())
    }
    viewModel {
        RegisterViewModel(get(), get(), get())
    }
    viewModel {
        SignOutViewModel(get())
    }
}