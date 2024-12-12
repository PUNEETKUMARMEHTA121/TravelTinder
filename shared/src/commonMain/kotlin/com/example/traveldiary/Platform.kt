package com.example.traveldiary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform