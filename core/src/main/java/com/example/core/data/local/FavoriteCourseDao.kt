package com.example.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.local.entity.FavoriteCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(course: FavoriteCourseEntity)

    @Query("DELETE FROM favorite_courses WHERE course_id = :courseId")
    suspend fun deleteByCourseId(courseId: Int)

    @Query("SELECT * FROM favorite_courses")
    fun getAllFavorites(): Flow<List<FavoriteCourseEntity>>

    @Query("SELECT * FROM favorite_courses WHERE course_id = :courseId")
    suspend fun getFavoriteByCourseId(courseId: Int): FavoriteCourseEntity?
}

