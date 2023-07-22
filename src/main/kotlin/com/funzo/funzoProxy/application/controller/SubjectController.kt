package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.CreateSubjectCommand
import com.funzo.funzoProxy.application.command.UpdateSubjectCommand
import com.funzo.funzoProxy.application.command.handler.CommandBus
import com.funzo.funzoProxy.application.controller.request.CreateSubjectRequest
import com.funzo.funzoProxy.application.controller.request.UpdateSubjectRequest
import com.funzo.funzoProxy.domain.subject.SubjectService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/subjects")
class SubjectController(private val commandBus: CommandBus, private val subjectService: SubjectService) {
    @PostMapping
    fun createSubject(@RequestBody createSubjectRequest: CreateSubjectRequest) {
        try {
            val command: CreateSubjectCommand = CreateSubjectCommand(
                createSubjectRequest.name, createSubjectRequest.description, createSubjectRequest.category)
            commandBus.dispatch(command)
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
            updateSubjectRequest.name, updateSubjectRequest.category, updateSubjectRequest.description)

            commandBus.dispatch(updateSubjectCommand)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
