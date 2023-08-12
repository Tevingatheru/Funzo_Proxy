package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.mapper.QuestionDtoMapper
import com.funzo.funzoProxy.application.query.QuestionsByExamCodeQuery
import com.funzo.funzoProxy.domain.question.QuestionService
import com.funzo.funzoProxy.infrastructure.dto.ExamQuestionsDto
import org.springframework.stereotype.Component

@Component
class QuestionsByExamCodeQueryHandler(
    private val questionService: QuestionService
): QueryHandler<ExamQuestionsDto, QuestionsByExamCodeQuery> {
    override fun handle(query: QuestionsByExamCodeQuery): ExamQuestionsDto {
        return QuestionDtoMapper.mapToQuestionsByExamCodeResponse(
            questionService.getQuestionsByExamCode(query.examCode).toMutableList()
        )
    }
}
