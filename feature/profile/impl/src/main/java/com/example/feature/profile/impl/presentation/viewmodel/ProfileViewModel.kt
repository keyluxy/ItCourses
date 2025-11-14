package com.example.feature.profile.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.core.R as coreR
import com.example.core.network.data.UserCourse
import com.example.feature.profile.impl.presentation.ProfileUiState

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(
        ProfileUiState(
            userCourses = listOf(
                UserCourse(
                    id = 1,
                    courseId = 1,
                    title = "3D-дженералист",
                    progress = 50,
                    currentLesson = 22,
                    totalLessons = 44,
                    rate = "3.9",
                    startDate = "10 Сентября 2024",
                    imageResId = coreR.drawable.ic_first_image
                ),
                UserCourse(
                    id = 2,
                    courseId = 2,
                    title = "Java-разработчик с нуля",
                    progress = 30,
                    currentLesson = 15,
                    totalLessons = 48,
                    rate = "4.9",
                    startDate = "29 мая 2024",
                    imageResId = coreR.drawable.ic_second_image
                )
            )
        )
    )
    val uiState: StateFlow<ProfileUiState> = _uiState
}





