package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.user.User
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserServiceImpl(private val userRepository: UserRepository){
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