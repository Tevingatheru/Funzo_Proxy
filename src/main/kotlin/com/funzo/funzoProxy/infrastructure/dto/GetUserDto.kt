package com.funzo.funzoProxy.infrastructure.dto

data class GetUserDto (
    val code: String,
    val email: String,
    val userType: String)
