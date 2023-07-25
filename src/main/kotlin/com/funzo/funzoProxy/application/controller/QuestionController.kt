package com.funzo.funzoProxy.application.controller

import com.funzo.funzoProxy.application.command.CommandBus
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
){
    @PostMapping
    fun addQuestion(@RequestBody addQuestionRequest: AddQuestionRequest){
        try {
            val addQuestionCommand : AddQuestionCommand = AddQuestionCommand(
            )
            commandBus.dispatch(addQuestionCommand)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @DeleteMapping
    fun removeQuestion(@RequestParam(value = "code") code: String){
        try {
            val removeQuestionCommand = RemoveQuestionCommand(

            )
            commandBus.dispatch(removeQuestionCommand)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @GetMapping
    fun getExamQuestions(){
        try {

        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @PutMapping
    fun modifyExamQuestion(){
        try {

        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}