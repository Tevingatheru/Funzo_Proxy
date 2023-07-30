package com.funzo.funzoProxy.infrastructure.dto

data class GetUserByCodeDto (
    val code: String,
    val email: String,
    val type: String)
