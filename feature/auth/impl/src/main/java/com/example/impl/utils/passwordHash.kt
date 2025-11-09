package com.example.impl.utils

import com.example.api.model.AuthUser
import kotlinx.coroutines.flow.Flow
import java.nio.charset.Charset
import java.security.MessageDigest

fun passwordHash(password: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    val bytes = md.digest(password.toByteArray(Charsets.UTF_8))
    return bytes.joinToString("") { "%02x".format(it) }
}

