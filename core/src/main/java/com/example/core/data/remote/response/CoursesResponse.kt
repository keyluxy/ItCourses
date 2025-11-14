package com.example.core.data.remote.response

import com.example.core.data.model.CourseData
import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("courses")
    val courses: List<CourseData>
)

