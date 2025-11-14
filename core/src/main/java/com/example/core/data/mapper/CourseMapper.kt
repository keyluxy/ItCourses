package com.example.core.data.mapper

import com.example.core.data.model.CourseData
import com.example.core.domain.model.Course

fun CourseData.toDomain(): Course {
    return Course(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}

fun Course.toData(): CourseData {
    return CourseData(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}

fun List<CourseData>.toDomain(): List<Course> {
    return this.map { it.toDomain() }
}

