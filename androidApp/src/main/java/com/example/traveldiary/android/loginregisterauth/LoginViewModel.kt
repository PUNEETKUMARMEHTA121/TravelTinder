package com.example.traveldiary.android.loginregisterauth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecktailor.core.Resource
import com.example.traveldiary.android.R
import com.example.traveldiary.android.common.base.BaseSharedPreference
import com.example.traveldiary.domain.model.LoginInputValidationType
import com.example.traveldiary.domain.usecase.ValidateLoginInputUseCase
import com.example.traveldiary.utils.Constants.IS_USER_LOGGED_IN
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException


class LoginViewModel(
    private val context: Context,
    private val loginUseCase: ValidateLoginInputUseCase,
    private val sharedPreference: BaseSharedPreference,
) : ViewModel() {
    private val _loginMessage = MutableStateFlow(LoginDetailsState())
    val loginMessage: StateFlow<LoginDetailsState> = _loginMessage

    fun validateLoginForm(
        emailInput: String = "",
        passwordInput: String = ""
    ): Pair<LoginInputValidationType, String?> {
        return when {
            emailInput.isEmpty() || passwordInput.isEmpty() ->
                Pair(
                    LoginInputValidationType.EmptyField,
                    context.getString(R.string.emptyFieldError)
                )

            !emailInput.contains('@') ->
                Pair(
                    LoginInputValidationType.NoEmail,
                    context.getString(R.string.invalidEmailError)
                )

            else ->
                Pair(LoginInputValidationType.Valid, null)
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loginMessage.value = LoginDetailsState(isLoading = true)
                val loginResult = loginUseCase.invoke(email, password)

                when (loginResult) {
                    is Resource.Success -> {
                        when (loginResult.data) {
                            "login Success" -> {
                                sharedPreference.put(IS_USER_LOGGED_IN, true)
                                _loginMessage.value =
                                    LoginDetailsState(data = context.getString(R.string.loggedInSuccessfully))
                            }

                            "email Not Exists" -> {
                                _loginMessage.value =
                                    LoginDetailsState(error = context.getString(R.string.emailNotFoundError))
                            }

                            "invalid Email Password" -> {
                                _loginMessage.value =
                                    LoginDetailsState(error = context.getString(R.string.invalidEmailPasswordError))
                            }

                            else -> {
                                _loginMessage.value =
                                    LoginDetailsState(error = context.getString(R.string.unknownError))
                            }
                        }
                    }

                    is Resource.Error -> {
                        _loginMessage.value = LoginDetailsState(
                            error = loginResult.message ?: context.getString(R.string.unknownError)
                        )
                    }

                    else -> {}
                }
            } catch (e: IOException) {
                _loginMessage.value = LoginDetailsState(
                    error = e.localizedMessage ?: context.getString(R.string.connectivityError)
                )
            } catch (e: Exception) {
                _loginMessage.value =
                    LoginDetailsState(error = context.getString(R.string.unknownError))
            }
        }
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPreference.getBoolean(IS_USER_LOGGED_IN)
    }
}
