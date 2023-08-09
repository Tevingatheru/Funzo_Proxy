package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.DeleteUserByCodeCommand
import com.funzo.funzoProxy.domain.user.UserService
import org.springframework.stereotype.Component

@Component
class DeleteUserByCodeCommandHandler(private val userService: UserService)
    : CommandHandler<String, DeleteUserByCodeCommand>
{
    override fun handle(command: DeleteUserByCodeCommand): String {
        return userService.deleteByCode(code = command.code)
    }
}