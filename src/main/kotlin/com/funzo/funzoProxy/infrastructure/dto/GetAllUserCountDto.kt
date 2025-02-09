package com.funzo.funzoProxy.infrastructure.dto

data class GetAllUserCountDto(
    val totalCount: Int,
    val adminCount : Int,
    val teacherCount : Int,
    val studentCount : Int
){

}
