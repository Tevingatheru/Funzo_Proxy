package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.AddUserDetailsCommand
import com.funzo.funzoProxy.application.command.DeleteUserByCodeCommand
import com.funzo.funzoProxy.application.command.bus.CommandBus
import com.funzo.funzoProxy.application.controller.request.CreateUserRequest
import com.funzo.funzoProxy.application.query.GetUserByCodeQuery
import com.funzo.funzoProxy.application.query.bus.QueryBus
import com.funzo.funzoProxy.infrastructure.dto.AddUserDetailsDto
import com.funzo.funzoProxy.infrastructure.dto.GetUserByCodeDto
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
    private val queryBus: QueryBus
) {
    @PostMapping
    fun createUser(createUserRequest: CreateUserRequest) : AddUserDetailsDto
    {
        return try {
            val command = AddUserDetailsCommand(
                createUserRequest.userType,
                createUserRequest.email
            )
            commandBus.dispatch(command)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping
    fun findByCode(@RequestParam code: String) : GetUserByCodeDto
    {
        try {
            return queryBus.execute(query = GetUserByCodeQuery(code = code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping
    fun deleteByCode(@RequestParam code: String) : String
    {
        try{
            return commandBus.dispatch(DeleteUserByCodeCommand(code = code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
