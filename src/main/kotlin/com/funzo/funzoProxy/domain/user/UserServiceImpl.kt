package com.funzo.funzoProxy.domain.user

import com.funzo.funzoProxy.infrastructure.GenerateCodeService
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
@Transactional
class UserServiceImpl(
    @Autowired private val userRepository: UserRepository,
    private val generateCodeService: GenerateCodeService
) : UserService
{
    override fun findByEmail(email: String) : User {
        return userRepository.findByEmail(email = email)
    }

    override fun findByCode(code: String): User {
        return try {
            userRepository.findByCode(code)
        } catch (e: EmptyResultDataAccessException) {
            throw NotFoundException()
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage, mapOf(Pair("code", code)), this::class.java)
            throw RuntimeException("Unable to find user by code")
        }
    }

    override fun save(userType: String, email: String): User {
        return try {
            if (noDuplicate(userType, email)) {
                val code: String = generateCodeService.generateCodeWithLength(7)
                val user = User(code, userType = UserType.find(userType), email)
                saveUser(user)
            } else {
                throw IllegalArgumentException("User already exists $email")
            }
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage, mapOf(Pair("email", email), Pair("type", userType)), this::class.java)
            throw RuntimeException("Unable to save user")
        }
    }

    override fun deleteByCode(code: String): String {
        return try {
            val user = this.findByCode(code)
            userRepository.delete(user)
            "OK"
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage, mapOf(Pair("code", code)), this::class.java)
            throw RuntimeException("Unable to find user by code")
        }
    }

    override fun findAll(): List<User> {
        try {
            return userRepository.findAll()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun getTotalUserCount(): Int {
        try {
            val userCount: Int = userRepository.getUserCount()
            LoggerUtils.log(level = LogLevel.INFO, message = "userCount: ${userCount}", className = this::class.java )
            return userCount
        } catch (e: Exception) {
            LoggerUtils.log(level = LogLevel.WARN, message = "Failed to get user count", className = this::class.java,
                diagnosisMap = mapOf(Pair("Error message", e.message!!), Pair("localizedMessage", e.localizedMessage))
            )
            throw RuntimeException(e)
        }
    }

    override fun getTotalAdminCount(): Int {
        try {
            return userRepository.getAdminCount()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun getTotalTeacherCount(): Int {
        try {
            return userRepository.getTeacherCount()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun getTotalStudentCount(): Int {
        try {
            return userRepository.getStudentCount()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun modifyUserEmail(email: String, code: String): User {
        return try {
            val user = this.findByCode(code)
            val userType = user.type.toString()
            if (noDuplicate(userType, email)) {
                user.email = email
                saveUser(user)
            } else {
                throw NotFoundException()
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun findTeacherByCode(code: String): User {
        return try {
            val user: User = userRepository.findByCode(code)
            if (user.isTeacher()) {
                LoggerUtils.log(
                    level = LogLevel.INFO,
                    message = "Fetching teacher",
                    className = this::class.java,
                    diagnosisMap = mapOf(Pair("user", user))
                )
                user
            } else {
                throw Exception()
            }
        } catch (e: EmptyResultDataAccessException) {
            throw NotFoundException()
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage, mapOf(Pair("code", code)), this::class.java)
            throw RuntimeException("Unable to find user by code")
        }
    }

    override fun userOfTypeExists(userCode: String, userType: UserType): Boolean {
        val user = findByCode(code = userCode)

        return user.type!! == userType
    }

    private fun saveUser(user: User) = userRepository.saveAndFlush(user)

    private fun noDuplicate(userType: String, email: String) =
        !this.checkIfUserExists(userType = userType, email = email)

    private fun checkIfUserExists(userType: String, email: String): Boolean {
        try {
            val checkIfUserExists = userRepository.checkIfUserExistsByTypeAndEmail(userType, email)
            return checkIfUserExists > 0
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
