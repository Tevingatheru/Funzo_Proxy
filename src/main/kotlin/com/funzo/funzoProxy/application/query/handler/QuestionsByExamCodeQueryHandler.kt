package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.controller.response.QuestionsByExamCodeResponse
import com.funzo.funzoProxy.application.mapper.QuestionDtoMapper
import com.funzo.funzoProxy.application.query.QuestionsByExamCodeQuery
import com.funzo.funzoProxy.domain.question.QuestionService
import org.springframework.stereotype.Component

@Component
class QuestionsByExamCodeQueryHandler(
    private val questionService: QuestionService
): QueryHandler<QuestionsByExamCodeResponse, QuestionsByExamCodeQuery> {
    override fun handle(query: QuestionsByExamCodeQuery): QuestionsByExamCodeResponse {
        return QuestionDtoMapper.mapToQuestionsByExamCodeResponse(
            questionService.getQuestionsByExamCode(query.examCode)
        )
    }
}
