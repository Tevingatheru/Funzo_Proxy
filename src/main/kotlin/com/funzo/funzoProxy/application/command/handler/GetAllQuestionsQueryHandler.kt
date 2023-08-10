package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.mapper.QuestionDtoMapper
import com.funzo.funzoProxy.application.query.GetAllQuestionsQuery
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.domain.question.QuestionService
import com.funzo.funzoProxy.infrastructure.dto.QuestionDto
import com.funzo.funzoProxy.infrastructure.dto.QuestionListDto
import org.springframework.stereotype.Component

@Component
class GetAllQuestionsQueryHandler(
    private val questionService: QuestionService
) : QueryHandler<QuestionListDto, GetAllQuestionsQuery>
{
    override fun handle(query: GetAllQuestionsQuery): QuestionListDto {
        return QuestionDtoMapper.mapToQuestionListDto(questionService.getAllQuestions())
    }
}
