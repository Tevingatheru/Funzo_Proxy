package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.bus.CommandBus
import com.funzo.funzoProxy.application.query.bus.QueryBus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/results")
class ResultController(
    private val commandBus: CommandBus,
                        private val queryBus: QueryBus) {

    @PostMapping
    fun createResult(
        @RequestParam examCode: String,
        @RequestParam userCode: String,
        @RequestParam score: Double
    ): CreateResultDto {
        return try {
            commandBus.dispatch(CreateResultCommand(examCode = examCode, userCode = userCode, score = score))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/{code}")
    fun findByCode(@PathVariable code: String): QueryResultDto {
        return try{
            queryBus.execute(findByCode(code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping
    fun findAll(): QueryAllResultsDto {
        return try {
            queryBus.execute(FindAllResultsQuery())
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }

    @GetMapping("/user/{userCode}")
    fun findResultsByUserCode(@PathVariable userCode: String): QueryResultsByUserCodeDto {
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
    ): ResultsByExamCodeAndUserCodeDto {
        return try {
            queryBus.execute(QueryResultsByExamCodeAndUserCode(examCode, userCode))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping("/{code}")
    fun deleteByCode(@PathVariable code: String) {
        try {
            commandBus.dispatch(DeleteResultByCode(code = code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
