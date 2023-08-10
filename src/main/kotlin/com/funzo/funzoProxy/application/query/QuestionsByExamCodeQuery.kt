package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.application.controller.response.QuestionsByExamCodeResponse

class QuestionsByExamCodeQuery(val examCode: String): Query<QuestionsByExamCodeResponse> {

}
