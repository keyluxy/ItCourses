package com.example.core.data.mapper

import com.example.core.data.local.entity.FavoriteCourseEntity
import com.example.core.domain.model.Course

fun Course.toFavoriteEntity(): FavoriteCourseEntity {
    return FavoriteCourseEntity(
        courseId = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        publishDate = this.publishDate
    )
}

fun FavoriteCourseEntity.toDomain(): Course {
    return Course(
        id = this.courseId,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = true, // When converting from FavoriteCourseEntity, it's always liked
        publishDate = this.publishDate
    )
}

fun List<FavoriteCourseEntity>.toDomain(): List<Course> {
    return this.map { it.toDomain() }
}

