package com.example.traveldiary.android.loginregisterauth


data class LoginDetailsState(
    val isLoading: Boolean = false,
    val data: String? = null,
    val error: String = ""
)