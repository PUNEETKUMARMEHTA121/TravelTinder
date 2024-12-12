package com.example.traveldiary.domain.model

enum class RegisterInputValidationType {
    EmptyField,
    NoName,
    NoEmail,
    PasswordNumberMissing,
    PasswordTooShort,
    Valid
}