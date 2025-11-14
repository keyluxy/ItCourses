package com.example.core.database.mapper

import com.example.core.database.entities.FavoriteCourseEntity
import com.example.core.network.data.Course

fun Course.toFavoriteEntity(): FavoriteCourseEntity {
    return FavoriteCourseEntity(
        courseId = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        publishDate = publishDate
    )
}

fun FavoriteCourseEntity.toCourse(): Course {
    return Course(
        id = courseId,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = true,
        publishDate = publishDate
    )
}

