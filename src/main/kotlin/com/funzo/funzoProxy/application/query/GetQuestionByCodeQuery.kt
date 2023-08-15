package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.QuestionDto

class GetQuestionByCodeQuery(val code: String) : Query<QuestionDto>
{

}
