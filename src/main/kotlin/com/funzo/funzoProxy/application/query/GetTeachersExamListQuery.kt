package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.ExamListDto

class GetTeachersExamListQuery(val userCode: String): Query<ExamListDto> {
}