package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetAllUserCountQuery
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.infrastructure.dto.GetAllUserCountDto
import org.springframework.stereotype.Component

@Component
class GetAllUserCountQueryHandler(
    private val userService: UserService
): QueryHandler<GetAllUserCountDto, GetAllUserCountQuery>  {
    override fun handle(query: GetAllUserCountQuery): GetAllUserCountDto {
        return mapToDto(userService.getTotalUserCount())
    }

    private fun mapToDto(total: Int) : GetAllUserCountDto {
        val getAllUserCountDto: GetAllUserCountDto = GetAllUserCountDto(total)

        return getAllUserCountDto
    }
}
