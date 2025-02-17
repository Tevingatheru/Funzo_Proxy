package com.funzo.funzoProxy.infrastructure.dto

data class AddUserDetailsDto(
    val code: String,
    val email: String,
    val userType: String?
)
