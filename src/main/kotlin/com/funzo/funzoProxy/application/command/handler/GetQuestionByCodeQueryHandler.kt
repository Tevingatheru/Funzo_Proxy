package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.mapper.QuestionDtoMapper
import com.funzo.funzoProxy.application.query.GetQuestionByCodeQuery
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.domain.question.QuestionService
import com.funzo.funzoProxy.infrastructure.dto.QuestionDto
import org.springframework.stereotype.Component

@Component
class GetQuestionByCodeQueryHandler(
    private val questionService: QuestionService
) : QueryHandler<QuestionDto, GetQuestionByCodeQuery> {
    override fun handle(query: GetQuestionByCodeQuery): QuestionDto {
        return QuestionDtoMapper.mapToQuestionDto(questionService.getQuestionByCode(query.code))
    }
}
