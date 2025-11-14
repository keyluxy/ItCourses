package com.example.core.data.remote

import com.example.core.data.remote.response.CoursesResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface CoursesApiService {
    @GET
    suspend fun getCourses(@Url url: String): CoursesResponse
}

