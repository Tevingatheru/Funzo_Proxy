package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.ExamDto

class GetExamByCodeQuery(val code: String): Query<ExamDto> {

}
