package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.CreateResultCommand
import com.funzo.funzoProxy.application.command.DeleteResultByCodeCommand
import com.funzo.funzoProxy.application.command.bus.CommandBus
import com.funzo.funzoProxy.application.controller.request.CreateResultRequest
import com.funzo.funzoProxy.application.query.FetchResultsByExamCodeAndUserCodeQuery
import com.funzo.funzoProxy.application.query.FetchUserByCodeQuery
import com.funzo.funzoProxy.application.query.FindAllResultsQuery
import com.funzo.funzoProxy.application.query.FindResultsByUserCodeQuery
import com.funzo.funzoProxy.application.query.bus.QueryBus
import com.funzo.funzoProxy.infrastructure.dto.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/results")
class ResultController(
    private val commandBus: CommandBus,
    private val queryBus: QueryBus) {

    @PostMapping
    fun createResult(
        @RequestBody request: CreateResultRequest
    ): CreateResultDto {
        return try {
            commandBus.dispatch(CreateResultCommand(examCode = request.examCode, userCode = request.userCode, score = request.score))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/code/{code}")
    fun findByCode(@PathVariable("code") code: String): QueryResultDto {
        return try{
            queryBus.execute(FetchUserByCodeQuery(code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/all")
    fun findAll(): QuerylResultListDto {
        return try {
            queryBus.execute(FindAllResultsQuery())
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }

    @GetMapping("/user/{userCode}")
    fun findResultsByUserCode(@PathVariable userCode: String): QuerylResultListDto {
        return try{
            queryBus.execute(FindResultsByUserCodeQuery(userCode = userCode))
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }

    @GetMapping("/exam/{examCode}/user/{userCode}")
    fun findResultsByExamCodeAndUserCode(
        @PathVariable examCode: String,
        @PathVariable userCode: String
    ): QuerylResultListDto {
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
}
