package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetAllExamsQuery
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.infrastructure.dto.GetAllUserDto
import org.springframework.stereotype.Component

@Component
class GetAllUserQueryHandler(
    private val userService: UserService
): QueryHandler<GetAllUserDto, GetAllExamsQuery>
{
    override fun handle(query: GetAllExamsQuery): GetAllUserDto {
        return mapToDto(userService.findAll())
    }

    private fun mapToDto(userList: List<User>): GetAllUserDto {
        val getAllUserDto = GetAllUserDto()
        userList.forEach {
            getAllUserDto.add(it)
        }
        return getAllUserDto
    }

}