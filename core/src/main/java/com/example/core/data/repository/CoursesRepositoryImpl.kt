package com.example.core.data.repository

import com.example.core.data.mapper.toDomain
import com.example.core.data.remote.CoursesApiService
import com.example.core.domain.model.Course
import com.example.core.domain.repository.ICoursesRepository
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val apiService: CoursesApiService
) : ICoursesRepository {

    private val coursesUrl = "https://drive.google.com/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download"

    override suspend fun getCourses(): Result<List<Course>> {
        return try {
            val response = apiService.getCourses(coursesUrl)
            Result.success(response.courses.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

