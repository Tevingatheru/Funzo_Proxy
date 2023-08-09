package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetUserByCodeQuery
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.infrastructure.dto.GetUserDto
import org.springframework.stereotype.Component

@Component
class GetUserByCodeQueryHandler(
    private val userService: UserService
): QueryHandler<GetUserDto, GetUserByCodeQuery>
{
    override fun handle(query: GetUserByCodeQuery): GetUserDto {
        return mapToDto(userService.findByCode(query.code))
    }

    private fun mapToDto(user: User): GetUserDto {
        return GetUserDto(
            code = user.code!!,
            email = user.email!!,
            userType = user.type!!.name
        )
    }
}
