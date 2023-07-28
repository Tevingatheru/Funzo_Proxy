package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.application.command.DeleteExamCommand
import com.funzo.funzoProxy.application.controller.request.CreateExamRequest
import com.funzo.funzoProxy.application.command.bus.CommandBusImpl
import com.funzo.funzoProxy.application.controller.request.SubjectExamListRequest
import com.funzo.funzoProxy.domain.exam.ExamListResponse
import com.funzo.funzoProxy.application.query.GetExamByCodeQuery
import com.funzo.funzoProxy.application.query.GetExamListBySubjectCodeQuery
import com.funzo.funzoProxy.application.query.bus.QueryBusImpl
import com.funzo.funzoProxy.domain.exam.ExamResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/exams")
class ExamController(
    private val commandBusImpl: CommandBusImpl,
    private val queryBusImpl: QueryBusImpl
)  {
    @PostMapping
    fun createExam(@RequestBody request: CreateExamRequest): ResponseEntity<String> {
        try {
            val command = CreateExamCommand(
                request.level,
                request.subjectCode
            )

            commandBusImpl.dispatch(command)
            return ResponseEntity.ok("Exam created successfully")
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping
    fun deleteExam(@RequestParam("code") code: String): ResponseEntity<String> {
        try {
            val command = DeleteExamCommand(
                code
            )

            commandBusImpl.dispatch(command)
            return ResponseEntity.ok("Exam deleted successfully")
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }

    @GetMapping
    fun getExamByCode(@RequestParam("code") code: String): ResponseEntity<ExamResponse> {
        try {
            val query = GetExamByCodeQuery(code = code)
            val examResponse: ExamResponse = queryBusImpl
                .execute(query)
            return ResponseEntity.ok(examResponse)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping
    fun getSubjectExamList(@RequestBody subjectExamListRequest: SubjectExamListRequest): ResponseEntity<ExamListResponse> {
        try {
            val query = GetExamListBySubjectCodeQuery(
                subjectCode = subjectExamListRequest.subjectCode
            )
            val examListResponse: ExamListResponse = queryBusImpl.execute(query)
            return ResponseEntity.ok(examListResponse)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
