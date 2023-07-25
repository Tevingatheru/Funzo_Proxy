package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.AddQuestionCommand
import com.funzo.funzoProxy.application.command.EditQuestionCommand
import com.funzo.funzoProxy.application.command.RemoveQuestionCommand
import com.funzo.funzoProxy.application.command.bus.CommandBus
import com.funzo.funzoProxy.application.controller.request.AddQuestionRequest
import com.funzo.funzoProxy.application.controller.request.EditQuestionRequest
import com.funzo.funzoProxy.application.controller.response.AddQuestionCommandResponse
import com.funzo.funzoProxy.application.controller.response.EditQuestionCommandResponse
import com.funzo.funzoProxy.application.controller.response.ExamQuestionsQueryResponse
import com.funzo.funzoProxy.application.query.ExamQuestionsQuery
import com.funzo.funzoProxy.application.query.bus.QueryBus
import org.springframework.http.ResponseEntity
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
    @PostMapping
    fun addQuestion(@RequestBody addQuestionRequest: AddQuestionRequest): ResponseEntity<AddQuestionCommandResponse>
    {
        try {
            val addQuestionCommand : AddQuestionCommand = AddQuestionCommand(
            )
            val addQuestionCommandResponse = commandBus.dispatch(addQuestionCommand)
            return ResponseEntity.ok(addQuestionCommandResponse)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping
    fun removeQuestion(@RequestParam(value = "code") questionCode: String): ResponseEntity<String>
    {
        try {
            val removeQuestionCommand = RemoveQuestionCommand(
                questionCode = questionCode
            )

            commandBus.dispatch(removeQuestionCommand)
            return ResponseEntity.ok("Deleted")
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping
    fun getExamQuestions(@RequestParam(value = "examCode", required = true) examCode: String)
    : ResponseEntity<ExamQuestionsQueryResponse>
    {
        try {
            val getExamQuestionsQuery = ExamQuestionsQuery(
                examCode = examCode
            )
            val queryResponse = queryBus.execute(getExamQuestionsQuery)
            return ResponseEntity.ok(queryResponse)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @PutMapping
    fun modifyExamQuestion(@RequestBody modifyQuestionRequest: EditQuestionRequest) : ResponseEntity<EditQuestionCommandResponse>
    {
        try {
            val editQuestionCommand = EditQuestionCommand (

            )

            val editQuestionCommandResponse = commandBus.dispatch(editQuestionCommand)
            return ResponseEntity.ok(editQuestionCommandResponse)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
