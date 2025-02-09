package com.funzo.funzoProxy.domain.user

enum class UserType(val type: String ) {
    ADMINISTRATOR("administrator"),
    STUDENT("student"),
    TEACHER("teacher");

    companion object {
        fun find(userType: String): UserType {
            return when(userType) {
                ADMINISTRATOR.type -> ADMINISTRATOR
                STUDENT.type -> STUDENT
                TEACHER.type -> TEACHER
                else -> {
                    throw IllegalArgumentException("User type: \"$userType\" does not exit")
                }
            }
        }
    }
}
