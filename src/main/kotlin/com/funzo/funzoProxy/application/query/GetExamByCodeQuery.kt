package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.domain.exam.ExamResponse

class GetExamByCodeQuery(val code: String): Query<ExamResponse> {

}
