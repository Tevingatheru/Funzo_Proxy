package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.TeachersExamListDto

class GetTeachersExamListQuery(val userCode: String): Query<TeachersExamListDto> {
}