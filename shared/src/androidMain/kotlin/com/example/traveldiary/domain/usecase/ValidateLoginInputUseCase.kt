package com.example.traveldiary.domain.usecase

import android.content.Context
import com.example.tecktailor.core.Resource
import com.example.traveldiary.R
import com.example.traveldiary.domain.repository.LoginDataSourceRepository
import java.io.IOException

actual class ValidateLoginInputUseCase(
    private val context: Context,
    private val repository: LoginDataSourceRepository
) {
    actual suspend operator fun invoke(
        email: String,
        password:String): Resource<String> {
            return try {
                val data = repository.loginUser(email, password)
                Resource.Success(data = data)
            } catch (e: IOException) {
                Resource.Error(message = e.localizedMessage ?: context.getString(R.string.checkConnectivityError))
            } catch (e: Exception) {
                Resource.Error(message = context.getString(R.string.unknownError, e.message))
            }
        }

}