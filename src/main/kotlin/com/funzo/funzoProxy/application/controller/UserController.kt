package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.AddUserDetailsCommand
import com.funzo.funzoProxy.application.command.ChangeUserEmailCommand
import com.funzo.funzoProxy.application.command.DeleteUserByCodeCommand
import com.funzo.funzoProxy.application.command.GetUserByEmailQuery
import com.funzo.funzoProxy.application.command.bus.CommandBus
import com.funzo.funzoProxy.application.controller.request.ChangeUserEmailRequest
import com.funzo.funzoProxy.application.controller.request.CreateUserRequest
import com.funzo.funzoProxy.application.query.GetAllUserCountQuery
import com.funzo.funzoProxy.application.query.GetAllUsersQuery
import com.funzo.funzoProxy.application.query.GetUserByCodeQuery
import com.funzo.funzoProxy.application.query.bus.QueryBus
import com.funzo.funzoProxy.infrastructure.dto.AddUserDetailsDto
import com.funzo.funzoProxy.infrastructure.dto.GetAllUserCountDto
import com.funzo.funzoProxy.infrastructure.dto.GetAllUserDto
import com.funzo.funzoProxy.infrastructure.dto.GetUserDto
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/users")
internal class UserController(
    private val commandBus: CommandBus,
    private val queryBus: QueryBus
) {
    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest) : AddUserDetailsDto
    {
        return try {
            LoggerUtils.log(LogLevel.INFO,"Creating user with email ${createUserRequest.email}", mapOf(), this::class.java)
            val command = AddUserDetailsCommand(
                createUserRequest.userType,
                createUserRequest.email
            )
            commandBus.dispatch(command)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/code")
    fun findUserByCode(@RequestParam code: String) : GetUserDto
    {
        try {
            return queryBus.execute(query = GetUserByCodeQuery(code = code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping
    fun deleteUserByCode(@RequestParam code: String) : String
    {
        try {
            return commandBus.dispatch(DeleteUserByCodeCommand(code = code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping
    fun getAllUsers(): GetAllUserDto {
        return try {
            val response = queryBus.execute(GetAllUsersQuery())
            LoggerUtils.log(level = LogLevel.INFO, message = "${response}", className = this::class.java)
            response
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/count")
    fun getAllUsersCount(): GetAllUserCountDto {
        return try {
            val response = queryBus.execute(GetAllUserCountQuery())
            LoggerUtils.log(level = LogLevel.INFO, message = "${response}", className = this::class.java)
            response
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @PutMapping("/edit/email")
    fun changEmail(@RequestBody request: ChangeUserEmailRequest): GetUserDto {
        return try {
            commandBus.dispatch(ChangeUserEmailCommand(userCode = request.code, email = request.email))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/email")
    fun getUserByCode(@RequestParam("email") email: String): GetUserDto {
        return try {
            LoggerUtils.log(
                level = LogLevel.INFO,
                message = "Feting user by email.",
                diagnosisMap = mapOf(Pair("email", email)),
                className = this::class.java
            )
            queryBus.execute(GetUserByEmailQuery(
                email = email
            ))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
