package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.CreateSubjectCommand
import com.funzo.funzoProxy.application.command.UpdateSubjectCommand
import com.funzo.funzoProxy.application.command.bus.CommandBus
import com.funzo.funzoProxy.application.controller.request.CreateSubjectRequest
import com.funzo.funzoProxy.application.controller.request.UpdateSubjectRequest
import com.funzo.funzoProxy.application.query.GetSubjectByCodeQuery
import com.funzo.funzoProxy.application.query.bus.QueryBus
import com.funzo.funzoProxy.domain.subject.SubjectService
import com.funzo.funzoProxy.infrastructure.dto.CreateSubjectDto
import com.funzo.funzoProxy.infrastructure.dto.GetSubjectByCodeQueryDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/subjects")
class SubjectController(
    private val commandBus: CommandBus,
    private val subjectService: SubjectService,
    private val queryBus: QueryBus
    ) {
    @PostMapping
    fun createSubject(@RequestBody createSubjectRequest: CreateSubjectRequest): CreateSubjectDto {
        try {
            val command: CreateSubjectCommand = CreateSubjectCommand(
                createSubjectRequest.name, createSubjectRequest.description, createSubjectRequest.category)
            return commandBus.dispatch(command)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping
    fun deleteSubject(@RequestParam("code") code: String) {
        try {
            subjectService.deleteSubjectByCode(code)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @PutMapping
    fun updateSubjectDetails(@RequestBody updateSubjectRequest: UpdateSubjectRequest) {
        try {
            val updateSubjectCommand: UpdateSubjectCommand = UpdateSubjectCommand(
            updateSubjectRequest.name, updateSubjectRequest.category, updateSubjectRequest.description,updateSubjectRequest.code)

            commandBus.dispatch(updateSubjectCommand)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping(produces = [])
    fun getSubjectByCode(@RequestParam("code") code: String)
    :  GetSubjectByCodeQueryDto {
        return try {
            val getSubjectByCodeQuery: GetSubjectByCodeQuery = GetSubjectByCodeQuery(
                code = code
            )

             queryBus.execute(getSubjectByCodeQuery)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
