package com.example.traveldiary.domain.usecase

import com.example.tecktailor.core.Resource

expect class ValidateRegisterInputUseCase {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
    ): Resource<String>
}