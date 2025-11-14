package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.UserDao
import com.example.core.database.entities.UserEntity
import com.example.core.data.local.FavoriteCourseDao
import com.example.core.data.local.entity.FavoriteCourseEntity

@Database(
    entities = [UserEntity::class, FavoriteCourseEntity::class],
    version = 2,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getFavoriteCourseDao(): FavoriteCourseDao
}