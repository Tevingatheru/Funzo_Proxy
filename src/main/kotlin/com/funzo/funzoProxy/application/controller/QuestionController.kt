package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.AddQuestionCommand
import com.funzo.funzoProxy.application.command.EditQuestionCommand
import com.funzo.funzoProxy.application.command.RemoveQuestionCommand
import com.funzo.funzoProxy.application.command.bus.CommandBus
import com.funzo.funzoProxy.application.controller.request.AddQuestionRequest
import com.funzo.funzoProxy.application.controller.request.EditQuestionRequest
import com.funzo.funzoProxy.application.controller.response.AddQuestionCommandResponse
import com.funzo.funzoProxy.application.controller.response.ExamQuestionsQueryResponse
import com.funzo.funzoProxy.application.query.ExamQuestionsQuery
import com.funzo.funzoProxy.application.query.GetAllQuestionsQuery
import com.funzo.funzoProxy.application.query.GetQuestionByCodeQuery
import com.funzo.funzoProxy.application.query.bus.QueryBus
import com.funzo.funzoProxy.domain.question.EditQuestionResponse
import com.funzo.funzoProxy.infrastructure.dto.QuestionDto
import com.funzo.funzoProxy.infrastructure.dto.QuestionListDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RequestMapping("/questions")
@RestController
class QuestionController (
    private val commandBus: CommandBus,
    private val queryBus: QueryBus
){
    @PostMapping("/add")
    fun addQuestion(@RequestBody addQuestionRequest: AddQuestionRequest): AddQuestionCommandResponse
    {
        try {
            val addQuestionCommand: AddQuestionCommand = AddQuestionCommand(
            )
            return commandBus.dispatch(addQuestionCommand)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping("/delete")
    fun removeQuestion(@RequestParam(value = "code") questionCode: String): String
    {
        try {
            val removeQuestionCommand = RemoveQuestionCommand(
                questionCode = questionCode
            )

            commandBus.dispatch(removeQuestionCommand)
            return "Deleted"
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/exam/code")
    fun getExamQuestions(@RequestParam(value = "examCode", required = true) examCode: String)
    : ExamQuestionsQueryResponse
    {
        try {
            val getExamQuestionsQuery = ExamQuestionsQuery(
                examCode = examCode
            )
            return queryBus.execute(getExamQuestionsQuery)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/code")
    fun getQuestionByCode(@RequestParam("code") code: String) : QuestionDto
    {
        return queryBus.execute(GetQuestionByCodeQuery(code = code))
    }

    @GetMapping("all")
    fun getAllQuestions() : QuestionListDto {
        return queryBus.execute(GetAllQuestionsQuery())
    }

    @PutMapping("/modify")
    fun modifyExamQuestion(@RequestBody modifyQuestionRequest: EditQuestionRequest) : EditQuestionResponse
    {
        try {
            val editQuestionCommand = EditQuestionCommand(
                examCode = modifyQuestionRequest.examCode,
                questionCode = modifyQuestionRequest.code,
                question = modifyQuestionRequest.question,
                questionType = modifyQuestionRequest.questionType,
                image = modifyQuestionRequest.image,
            )

            return commandBus.dispatch(editQuestionCommand)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}