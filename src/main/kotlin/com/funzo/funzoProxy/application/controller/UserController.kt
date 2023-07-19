package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.AddUserDetailsCommand
import com.funzo.funzoProxy.application.command.handler.CommandBus
import com.funzo.funzoProxy.application.controller.request.CreateUserRequest
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.infrastructure.jpa.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/users")
internal class UserController(
    private val commandBus: CommandBus,
    private val userServiceImpl: UserServiceImpl) {
    @PostMapping
    fun createUser(createUserRequest: CreateUserRequest) : ResponseEntity<Any> {
        try {
            val command = AddUserDetailsCommand(
                createUserRequest.userType,
                createUserRequest.email
            )
            val user: Any = commandBus.dispatch(command)
            return ResponseEntity.ok(user)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping
    fun findByCode(@RequestParam code: String) : ResponseEntity<User> {
        try {
            return ResponseEntity.ok(userServiceImpl.findByCode(code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping
    fun deleteByCode(@RequestParam code: String) : ResponseEntity<String> {
        try{
            return ResponseEntity.ok(userServiceImpl.deleteByCode(code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
