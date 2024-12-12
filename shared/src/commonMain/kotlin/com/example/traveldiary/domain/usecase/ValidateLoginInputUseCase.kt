package com.example.traveldiary.domain.usecase

import com.example.tecktailor.core.Resource


//@kamakshi pass values using model classes like Login.kt, Register.kt
expect class ValidateLoginInputUseCase {
    suspend operator fun invoke(
        email: String,
        password:String):
            Resource<String>
}