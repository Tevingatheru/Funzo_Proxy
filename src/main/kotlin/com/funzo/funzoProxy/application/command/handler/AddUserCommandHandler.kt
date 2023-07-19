package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.AddUserDetailsCommand
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserType
import com.funzo.funzoProxy.infrastructure.GenerateCodeService
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
//@Transactional
class AddUserCommandHandler(private val userRepositoryImpl: UserRepository,
                            private val generateCodeService: GenerateCodeService) {
    fun handle(addUserDetailsCommand: AddUserDetailsCommand): User {
        val code: String = generateCodeService.generateCodeWithLength(7)
        val user = User(code, userType = UserType.valueOf(addUserDetailsCommand.userType), addUserDetailsCommand.email)
        return userRepositoryImpl.save(user)
    }
}
