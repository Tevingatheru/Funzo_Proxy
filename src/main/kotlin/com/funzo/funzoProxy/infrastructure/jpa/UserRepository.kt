package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserType
import com.funzo.funzoProxy.infrastructure.dao.UserCountDao
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

    @Query(value = "SELECT * FROM users WHERE code = :userCode and type = 'STUDENT'",
        nativeQuery = true)
    fun findStudentByUserCode(userCode: String): User

    @Query(value = "SELECT * FROM users WHERE email = :email",
        nativeQuery = true)
    fun findByEmail(@Param("email") email: String): User

    @Query(value = "SELECT COUNT(*) as user_count FROM users", nativeQuery = true)
    fun getUserCount(): Int

    @Query(value = "SELECT COUNT(*) as user_count FROM users WHERE type = 'ADMINISTRATOR' ", nativeQuery = true)
    fun getAdminCount(): Int
    @Query(value = "SELECT COUNT(*) as user_count FROM users WHERE type = 'TEACHER' ", nativeQuery = true)
    fun getTeacherCount(): Int
    @Query(value = "SELECT COUNT(*) as user_count FROM users WHERE type = 'STUDENT' ", nativeQuery = true)
    fun getStudentCount(): Int
}
