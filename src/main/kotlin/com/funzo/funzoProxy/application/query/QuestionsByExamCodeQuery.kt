package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.ExamQuestionsDto

class QuestionsByExamCodeQuery(val examCode: String): Query<ExamQuestionsDto> {

}
