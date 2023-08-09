package com.funzo.funzoProxy.infrastructure.dto

import com.funzo.funzoProxy.domain.user.User

data class GetAllUserDto (var users: List<GetUserDto>? = mutableListOf()) {
    fun add (user: User) {
        this.users!!.plus(GetUserDto(
            code = user.code!!,
            email = user.email!!,
            userType = user.type!!.type!!
        )).also { users = it }
    }
}
