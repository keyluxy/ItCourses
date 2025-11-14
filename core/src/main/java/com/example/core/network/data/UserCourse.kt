package com.example.core.network.data

data class UserCourse(
    val id: Int,
    val courseId: Int,
    val title: String,
    val progress: Int, // Процент выполнения (0-100)
    val currentLesson: Int, // Текущий урок
    val totalLessons: Int, // Всего уроков
    val rate: String,
    val startDate: String,
    val imageResId: Int
)

