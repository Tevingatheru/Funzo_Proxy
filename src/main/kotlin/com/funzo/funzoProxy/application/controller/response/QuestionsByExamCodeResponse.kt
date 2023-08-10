package com.funzo.funzoProxy.application.controller.response

import com.funzo.funzoProxy.infrastructure.dto.QuestionDto

data class QuestionsByExamCodeResponse(
    val questions: List<QuestionDto>
)
{

}
