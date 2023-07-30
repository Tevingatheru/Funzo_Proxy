package com.funzo.funzoProxy.domain.user

import com.funzo.funzoProxy.infrastructure.GenerateCodeService
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.beans.factory.annotation.Autowired
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
    override fun findByCode(code: String): User {
        return try {
            userRepository.findByCode(code)
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage, mapOf(Pair("code", code)), this::class.java)
            throw RuntimeException("Unable to find user by code")
        }
    }

    override fun save(userType: String, email: String): User {
        return try {
            val code: String = generateCodeService.generateCodeWithLength(7)
            val user = User(code, userType = UserType.valueOf(userType), email)
            userRepository.save(user)
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage, mapOf(Pair("email", email)), this::class.java)
            throw RuntimeException("Unable to save user")
        }
    }

    override fun deleteByCode(code: String): String {
        return try {
            val user = userRepository.findByCode(code)
            userRepository.delete(user)
            "OK"
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage, mapOf(Pair("code", code)), this::class.java)
            throw RuntimeException("Unable to find user by code")
        }
    }
}
