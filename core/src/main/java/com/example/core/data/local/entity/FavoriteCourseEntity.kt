package com.example.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_courses",
    indices = [Index(value = ["course_id"], unique = true)]
)
data class FavoriteCourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "course_id")
    val courseId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "price")
    val price: String,

    @ColumnInfo(name = "rate")
    val rate: String,

    @ColumnInfo(name = "start_date")
    val startDate: String,

    @ColumnInfo(name = "publish_date")
    val publishDate: String,
)

