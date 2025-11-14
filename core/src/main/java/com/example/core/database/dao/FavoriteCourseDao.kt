package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.entities.FavoriteCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteCourseEntity): Long

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteCourseEntity)

    @Query("SELECT * FROM favorite_courses")
    fun getAllFavorites(): Flow<List<FavoriteCourseEntity>>

    @Query("SELECT * FROM favorite_courses WHERE course_id = :courseId")
    suspend fun getFavoriteByCourseId(courseId: Int): FavoriteCourseEntity?

    @Query("DELETE FROM favorite_courses WHERE course_id = :courseId")
    suspend fun deleteByCourseId(courseId: Int)
}

