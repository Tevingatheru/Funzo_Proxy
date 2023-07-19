package com.funzo.funzoProxy.infrastructure

import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Repository
@Transactional
class UserRepositoryImpl(private val userRepository: UserRepository){
    fun findByCode(code: String): User {
        try {
            return userRepository.findByCode(code)
        } catch (e: Exception) {
            throw RuntimeException("Unable to find user by code")
        }
    }

    fun save(user: User): User {
        try {
            return userRepository.save(user)
        } catch (e: Exception) {
            throw RuntimeException("Unable to save user")
        }
    }

    fun deleteByCode(code: String): String {
        try {
            val user = userRepository.findByCode(code)
            userRepository.delete(user)
            return "OK"
        } catch (e: Exception) {
            throw RuntimeException("Unable to find user by code")
        }
    }
}