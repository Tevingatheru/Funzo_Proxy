package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.AddUserDetailsCommand
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.infrastructure.dto.AddUserDetailsDto
import org.springframework.stereotype.Component

@Component
class AddUserCommandHandler(
    private val userService: UserService
): CommandHandler<AddUserDetailsDto, AddUserDetailsCommand>
{
    override fun handle(command: AddUserDetailsCommand): AddUserDetailsDto {
        return mapToDto(userService.save(command.userType, command.email))
    }

    private fun mapToDto(user: User): AddUserDetailsDto {
        return AddUserDetailsDto(
            userCode = user.code!!,
            email = user.email!!,
            userType = user.type!!.type
        )
    }
}
