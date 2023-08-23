package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.ChangeUserEmailCommand
import com.funzo.funzoProxy.application.mapper.UserMapper
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.infrastructure.dto.GetUserDto
import org.springframework.stereotype.Component

@Component
class ChangeUserEmailCommandHandler(
    private val userService: UserService
): CommandHandler<GetUserDto, ChangeUserEmailCommand>
{
    override fun handle(command: ChangeUserEmailCommand): GetUserDto {
        return if (command.email.isNotBlank()) {
            UserMapper.mapToDto(userService.modifyUserEmail(
                email = command.email,
                code = command.userCode
            ))
        } else {
            throw IllegalArgumentException()
        }
    }
}