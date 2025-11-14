package com.example.feature.profile.impl.presentation

import com.example.core.network.data.UserCourse

data class ProfileUiState(
    val title: String = "Профиль",
    val userCourses: List<UserCourse> = emptyList()
)





