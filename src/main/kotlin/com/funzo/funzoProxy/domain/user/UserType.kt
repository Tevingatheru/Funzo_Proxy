package com.funzo.funzoProxy.domain.user

enum class UserType(val type: String? = null) {
    ADMINISTRATOR("administrator"),
    STUDENT("student"),
    TEACHER("teacher")
}
