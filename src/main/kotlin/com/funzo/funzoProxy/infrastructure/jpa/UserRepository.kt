package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE code = :userCode and type = 'STUDENT'",
        nativeQuery = true)
    fun findStudentByUserCode(userCode: String): User

}
