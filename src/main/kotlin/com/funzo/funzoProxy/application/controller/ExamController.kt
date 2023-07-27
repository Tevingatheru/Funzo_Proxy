package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.application.command.AddQuestionCommand
import com.funzo.funzoProxy.application.controller.request.CreateExamRequest
import com.funzo.funzoProxy.application.command.bus.CommandBusImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/exams")
class ExamController(private val commandBusImpl: CommandBusImpl)  {
    @PostMapping
    fun createExam(@RequestBody request: CreateExamRequest): ResponseEntity<String> {
        val command = CreateExamCommand(
            request.level,
            request.questions.map {
                AddQuestionCommand(it.questionText, it.image, it.questionType)
            }
        )

        commandBusImpl.dispatch(command)
        return ResponseEntity.ok("Exam created successfully")
    }
}