package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.application.command.DeleteExamCommand
import com.funzo.funzoProxy.application.controller.request.CreateExamRequest
import com.funzo.funzoProxy.application.command.bus.CommandBusImpl
import com.funzo.funzoProxy.application.controller.response.CreateExamCommandResponse
import com.funzo.funzoProxy.application.query.*
import com.funzo.funzoProxy.infrastructure.dto.ExamListDto
import com.funzo.funzoProxy.application.query.bus.QueryBusImpl
import com.funzo.funzoProxy.infrastructure.dto.ExamDto
import com.funzo.funzoProxy.infrastructure.dto.TeachersExamListDto
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
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
    fun createExam(@RequestBody request: CreateExamRequest): CreateExamCommandResponse {
        return try {
            if (request.examDescription.isBlank()) {
                throw Exception("Bad Request")
            }

            val command = CreateExamCommand(
                subjectCode = request.subjectCode,
                userCode = request.userCode,
                examDescription = request.examDescription,
            )

            commandBusImpl.dispatch(command)
        } catch (e: Exception) {
            throw RuntimeException(e.localizedMessage)
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
            throw RuntimeException(e.localizedMessage)
        }
    }

    @GetMapping("/code")
    fun getExamByCode(@RequestParam("code") code: String): ExamDto {
        return try {
            val query = GetExamByCodeQuery(code = code)

            queryBusImpl.execute(query)
        } catch (e: NotFoundException) {
            throw NotFoundException()
        } catch (e: Exception) {
            throw RuntimeException(e.localizedMessage)
        }
    }

    @GetMapping("/subject")
    fun getSubjectExamList(@RequestParam subjectCode: String): ExamListDto {
        return try {
            LoggerUtils.log(
                level = LogLevel.INFO,
                message = "Fetching exams by subject code: $subjectCode",
                className = this::class.java
            )
            val query: GetExamListBySubjectCodeQuery = GetExamListBySubjectCodeQuery(
                subjectCode = subjectCode
            )

            queryBusImpl.execute(query)
        } catch (e: Exception) {
            throw RuntimeException(e.localizedMessage)
        }
    }

    @GetMapping()
    fun getAllExams(): ExamListDto {
        return try {
            LoggerUtils.log(
                level = LogLevel.INFO,
                message = "Fetching all exams.",
                className = this::class.java
            )

            val query : GetExamListQuery = GetExamListQuery()

            queryBusImpl.execute(query)
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage, this::class.java)
            throw RuntimeException(e.localizedMessage)
        }
    }

    @GetMapping("/teacher")
    fun getAllTeachersExams(@RequestParam(value = "userCode") userCode: String): TeachersExamListDto {
        return try {
            LoggerUtils.log(
                level = LogLevel.INFO,
                message = "Fetching all teachers exams.",
                className = this::class.java
            )
            val query : GetTeachersExamListQuery = GetTeachersExamListQuery(
                userCode = userCode
            )

            queryBusImpl.execute(query)
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage, this::class.java)
            throw RuntimeException(e.localizedMessage)
        }
    }
}
