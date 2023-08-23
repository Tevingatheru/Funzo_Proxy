package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.GetUserByEmailQuery
import com.funzo.funzoProxy.application.mapper.UserMapper
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.infrastructure.dto.GetUserDto
import org.springframework.stereotype.Component

@Component
class GetUserByEmailQueryHandler(
    private val userService: UserService
): QueryHandler<GetUserDto, GetUserByEmailQuery>
{
    override fun handle(query: GetUserByEmailQuery): GetUserDto {
        return if (query.email.isNotBlank()) {
            UserMapper.mapToDto(userService.findByEmail(
                email = query.email
            ))
        } else {
            throw IllegalArgumentException()
        }
    }
}
