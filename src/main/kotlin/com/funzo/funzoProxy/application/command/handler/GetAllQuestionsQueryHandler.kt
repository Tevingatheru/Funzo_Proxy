package com.funzo.funzoProxy.application.command.handler

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
)
    : QueryHandler<QuestionListDto, GetAllQuestionsQuery>
{
    override fun handle(query: GetAllQuestionsQuery): QuestionListDto {
        return mapToDto(questionService.getAllQuestions())
    }

    private fun mapToDto(allQuestions: List<Question>): QuestionListDto {
        val questionListDto : QuestionListDto = QuestionListDto()
        allQuestions.forEach {
            questionListDto.add(QuestionDto(
                examCode = it.exam.code,
                questionType = it.type.toString(),
                text = it.question,
                code = it.code
            ))
        }
        return questionListDto
    }
}
