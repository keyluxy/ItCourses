package com.example.impl.data.mapper

import com.example.api.model.AuthUser
import com.example.core.database.entities.UserEntity

internal object AuthMapper {
    internal fun toDomain(entity: UserEntity): AuthUser =
        AuthUser(id = entity.id, email = entity.email)
}