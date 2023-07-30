package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetUserByCodeQuery
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.infrastructure.dto.GetUserByCodeDto
import org.springframework.stereotype.Component

@Component
class GetUserByCodeQueryHandler(
    private val userService: UserService
): QueryHandler<GetUserByCodeDto, GetUserByCodeQuery>
{
    override fun handle(query: GetUserByCodeQuery): GetUserByCodeDto {
        return mapToDto(userService.findByCode(query.code))
    }

    private fun mapToDto(user: User): GetUserByCodeDto {
        return GetUserByCodeDto(
            code = user.code!!,
            email = user.email!!,
            type = user.type!!.name
        )
    }
}
