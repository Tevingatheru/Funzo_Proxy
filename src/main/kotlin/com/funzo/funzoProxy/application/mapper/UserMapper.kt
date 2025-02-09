package com.funzo.funzoProxy.application.mapper

import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.infrastructure.dto.GetUserDto

object UserMapper {
    fun mapToDto(user: User): GetUserDto {
        return GetUserDto(
            user.code!!,
            user.email!!,
            user.type!!.type
        )
    }
}
