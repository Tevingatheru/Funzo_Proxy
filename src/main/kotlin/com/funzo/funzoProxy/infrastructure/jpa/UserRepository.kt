package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByCode(code: String): User
    @Query(value = "SELECT EXISTS (SELECT 1 FROM users WHERE users.email = :email AND users.type = :type )",
        nativeQuery = true)
    fun checkIfUserExistsByTypeAndEmail(@Param("type") userType: String, @Param("email") email: String): Int

}
