package com.example.traveldiary.android.landingScreen.model

data class Trip(
    val id: Int,
    val destination: String,
    val startDate: String,
    val endDate: String,
    val teamSize: Int,
    val description: String,
    val organizer: String,
    val imageUrl: List<String>,
    val createdDate: String,
) {
    companion object {
        fun empty() = Trip(-1, "", "", "", 0, "", "", listOf(), "")
    }
}
