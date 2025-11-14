package com.example.core.network.domain

import com.example.core.network.data.Course

interface ICoursesRepository {
    suspend fun getCourses(): Result<List<Course>>
}