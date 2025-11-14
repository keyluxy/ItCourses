package com.example.core.network.domain

import com.example.core.network.data.Course
import com.example.core.network.response.CoursesResponse
import com.example.core.network.service.APIService
import javax.inject.Inject

class CoursesRepository @Inject constructor(
    private val apiService: APIService
) : ICoursesRepository {

    private val coursesUrl = "https://drive.google.com/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download"

    override suspend fun getCourses(): Result<List<Course>> {
        return try {
            val response: CoursesResponse = apiService.getCourses(coursesUrl)
            Result.success(response.courses)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}