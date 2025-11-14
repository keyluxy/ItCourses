package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.core.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: UserEntity): Long

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun findUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email AND password_hash = :passwordHash")
    suspend fun authenticateUser(email: String, passwordHash: String): UserEntity?

    @Query("SELECT * FROM users")
     fun getALlUsers(): Flow<List<UserEntity>>
}
