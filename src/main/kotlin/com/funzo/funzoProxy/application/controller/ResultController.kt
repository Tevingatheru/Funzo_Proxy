package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.CreateResultCommand
import com.funzo.funzoProxy.application.command.DeleteResultByCodeCommand
import com.funzo.funzoProxy.application.command.bus.CommandBus
import com.funzo.funzoProxy.application.controller.request.CreateResultRequest
import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByAdminCodeResponse
import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByStudentCodeResponse
import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByTeacherCodeResponse
import com.funzo.funzoProxy.application.query.*
import com.funzo.funzoProxy.application.query.bus.QueryBus
import com.funzo.funzoProxy.infrastructure.dto.*
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/results")
class ResultController(
    private val commandBus: CommandBus,
    private val queryBus: QueryBus) {

    @PostMapping("/create")
    fun createResult(
        @RequestBody request: CreateResultRequest
    ): CreateResultDto {
        return try {
            LoggerUtils.log(level = LogLevel.INFO, message = "Add results.", className = this::class.java)
            commandBus.dispatch(CreateResultCommand(examCode = request.examCode, userCode = request.userCode, score = request.score))
        } catch (e: Exception) {
            LoggerUtils.log(
                LogLevel.ERROR, "Unable to create result",
                mapOf(
                    Pair("examCode", request.examCode),
                    Pair("userCode", request.userCode)), this::class.java)
            throw RuntimeException(e)
        }
    }

    @GetMapping("/code/{code}")
    fun findByCode(@PathVariable("code") code: String): QueryResultDto? {
        return try {
            queryBus.execute(FetchUserByCodeQuery(code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/all")
    fun findAll(): QueryResultListDto {
        return try {
            queryBus.execute(FindAllResultsQuery())
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, "Unable to get results", this::class.java)
            throw RuntimeException(e)
        }
    }

    @GetMapping("/user/{userCode}")
    fun findResultsByUserCode(@PathVariable userCode: String): QueryResultListDto {
        return try{
            queryBus.execute(FindResultsByUserCodeQuery(userCode = userCode))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/exam/{examCode}/user/{userCode}")
    fun findResultsByExamCodeAndUserCode(
        @PathVariable examCode: String,
        @PathVariable userCode: String
    ): QueryResultListDto {
        return try {
            queryBus.execute(FetchResultsByExamCodeAndUserCodeQuery(examCode, userCode))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping("/delete/{code}")
    fun deleteByCode(@PathVariable code: String) {
        try {
            commandBus.dispatch(DeleteResultByCodeCommand(code = code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/student/stats/")
    fun getStudentStats(@RequestParam(value = "studentCode") studentCode: String): GetResultsStatsByStudentCodeResponse {
        try {
            val response : GetResultsStatsByStudentCodeResponse = queryBus.execute(GetResultsStatsByStudentCodeQuery(studentCode = studentCode))
            return response
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/teacher/stats/")
    fun getTeacherStats(@RequestParam(value = "teacherCode") teacherCode: String): GetResultsStatsByTeacherCodeResponse {
        try {
            val response: GetResultsStatsByTeacherCodeResponse = queryBus.execute(GetResultsStatsByTeacherCodeQuery(teacherCode = teacherCode))
            return response
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/admin/stats/")
    fun getAdminStats(@RequestParam(value = "adminCode") adminCode: String): GetResultsStatsByAdminCodeResponse {
        try {
            val response: GetResultsStatsByAdminCodeResponse = queryBus.execute(GetResultsStatsByAdminCodeQuery(adminCode = adminCode))
            return response
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
