package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByCode(code: String): User
}