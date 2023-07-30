package com.funzo.funzoProxy.domain.user

interface UserService {

    fun findByCode(code: String): User
    fun save(userType: String, email: String): User
    fun deleteByCode(code: String): String
}
