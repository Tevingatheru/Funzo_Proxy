package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.CreateOptionCommand
import com.funzo.funzoProxy.application.command.DeleteOptionByCodeCommand
import com.funzo.funzoProxy.application.command.ModifyOptionCommand
import com.funzo.funzoProxy.application.command.bus.CommandBus
import com.funzo.funzoProxy.application.controller.request.CreateOptionRequest
import com.funzo.funzoProxy.application.controller.request.ModifyOptionRequest
import com.funzo.funzoProxy.application.query.GetAllOptionsQuery
import com.funzo.funzoProxy.application.query.GetOptionByCodeQuery
import com.funzo.funzoProxy.application.query.GetQuestionOptionsQuery
import com.funzo.funzoProxy.application.query.bus.QueryBus
import com.funzo.funzoProxy.infrastructure.dto.OptionDto
import com.funzo.funzoProxy.infrastructure.dto.OptionListDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/options")
class OptionController(
    private val commandBus: CommandBus,
    private val queryBus: QueryBus
) {
    @PostMapping("/create")
    fun createOption(@RequestBody request: CreateOptionRequest) : OptionDto {
        return try {
            commandBus.dispatch(CreateOptionCommand(
                optionA = request.optionA,
                optionB = request.optionB,
                optionC = request.optionC,
                optionD = request.optionD,
                correctOption = request.correctOption,
                questionCode = request.questionCode
            ))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping("/delete")
    fun deleteByCode(@RequestParam("code") code: String) {
        try {
            commandBus.dispatch(DeleteOptionByCodeCommand(code = code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/code")
    fun getByCode(@RequestParam("code") code: String): OptionDto {
        return try {
            queryBus.execute(GetOptionByCodeQuery(code = code))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/question/code")
    fun getOptionsByQuestionCode(@RequestParam("questionCode") questionCode: String): OptionListDto {
        return try {
            queryBus.execute(GetQuestionOptionsQuery(questionCode = questionCode))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping("/all")
    fun getAllOptions() : OptionListDto {
        return try {
            queryBus.execute(GetAllOptionsQuery())
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @PutMapping("edit")
    fun modifyOption(@RequestBody request: ModifyOptionRequest): OptionDto {
        return try {
            commandBus.dispatch(ModifyOptionCommand(optionA = request.optionA,
                optionB = request.optionB,
                optionC = request.optionC,
                optionD = request.optionD,
                correctOption = request.correctOption,
                code = request.code
                ))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
