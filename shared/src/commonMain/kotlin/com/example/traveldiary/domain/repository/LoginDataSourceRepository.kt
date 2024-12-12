package com.example.traveldiary.domain.repository


interface LoginDataSourceRepository {

    suspend fun loginUser(email: String, password:String): String

}