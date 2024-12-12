package com.example.traveldiary.android.loginregisterauth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecktailor.core.Resource
import com.example.traveldiary.android.R
import com.example.traveldiary.android.common.base.BaseSharedPreference
import com.example.traveldiary.domain.model.RegisterInputValidationType
import com.example.traveldiary.domain.usecase.ValidateRegisterInputUseCase
import com.example.traveldiary.utils.Constants.IS_USER_LOGGED_IN
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class RegisterViewModel(
    private val context: Context,
    private val registerUseCase: ValidateRegisterInputUseCase,
    private val sharedPreference: BaseSharedPreference
) : ViewModel()
{
    private val _registerMessage = MutableStateFlow(RegisterDetailsState())
    val registerMessage: StateFlow<RegisterDetailsState> = _registerMessage

    fun validateRegisterForm(
        nameInput: String = "",
        emailInput: String = "",
        passwordInput: String = ""
    ): Pair<RegisterInputValidationType, String?> {
        return when {
            nameInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty() ->
                Pair(
                    RegisterInputValidationType.EmptyField,
                    context.getString(R.string.emptyFieldError)
                )

            nameInput.length < 2 ->
                Pair(
                    RegisterInputValidationType.NoName,
                    context.getString(R.string.invalidNameError)
                )

            !emailInput.contains('@') ->
                Pair(
                    RegisterInputValidationType.NoEmail,
                    context.getString(R.string.invalidEmailError)
                )

            !passwordInput.any { it.isDigit() } ->
                Pair(
                    RegisterInputValidationType.PasswordNumberMissing,
                    context.getString(R.string.passwordNumberMissingError)
                )

            passwordInput.length < 6 ->
                Pair(
                    RegisterInputValidationType.PasswordTooShort,
                            context.getString(R.string.passwordTooShortError)
                )

            else ->
                Pair(RegisterInputValidationType.Valid, null)
        }
    }


    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _registerMessage.value = RegisterDetailsState(isLoading = true)

                val registerResult = registerUseCase.invoke(name, email, password)

                when (registerResult) {
                    is Resource.Success -> {
                        when (registerResult.data) {
                            "success" -> {
                                sharedPreference.put(IS_USER_LOGGED_IN, true)
                                _registerMessage.value =
                                    RegisterDetailsState(data = context.getString(R.string.registrationSuccess))
                            }

                            "weak password" -> _registerMessage.value =
                                RegisterDetailsState(error = context.getString(R.string.weakPasswordError))

                            "malformed email" -> _registerMessage.value =
                                RegisterDetailsState(error = context.getString(R.string.malformedEmailError))

                            "email already exists" -> _registerMessage.value =
                                RegisterDetailsState(error = context.getString(R.string.emailExistsError))

                            else -> _registerMessage.value =
                                RegisterDetailsState(error = context.getString(R.string.unknownError))
                        }
                    }

                    is Resource.Error -> {
                        _registerMessage.value = RegisterDetailsState(
                            error = registerResult.message
                                ?: context.getString(R.string.unknownError)
                        )
                    }

                    else -> {}
                }

            } catch (e: IOException) {
                _registerMessage.value = RegisterDetailsState(
                    error = e.localizedMessage ?: context.getString(R.string.checkConnectivity)
                )
            } catch (e: Exception) {
                _registerMessage.value =
                    RegisterDetailsState(error = context.getString(R.string.unknownError))
            }
        }
    }
}
