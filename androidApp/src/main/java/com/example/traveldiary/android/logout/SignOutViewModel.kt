package com.example.traveldiary.android.logout

import androidx.lifecycle.ViewModel
import com.example.traveldiary.android.common.base.BaseSharedPreference
import com.example.traveldiary.utils.Constants.IS_USER_LOGGED_IN

class SignOutViewModel(
    private val sharedPreference: BaseSharedPreference,
) : ViewModel() {

    fun signOut() {
        sharedPreference.put(IS_USER_LOGGED_IN, false)
    }
}