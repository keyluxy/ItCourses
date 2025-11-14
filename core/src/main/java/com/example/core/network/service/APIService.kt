package com.example.core.network.service

import com.example.core.network.response.CoursesResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getCourses(@Url url: String): CoursesResponse

}