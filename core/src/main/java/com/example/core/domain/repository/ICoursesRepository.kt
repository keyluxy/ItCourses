package com.example.core.domain.repository

import com.example.core.domain.model.Course

interface ICoursesRepository {
    suspend fun getCourses(): Result<List<Course>>
}

