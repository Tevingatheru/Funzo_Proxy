package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateOptionCommand
import com.funzo.funzoProxy.application.mapper.OptionMapper
import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.infrastructure.dto.OptionDto
import org.springframework.stereotype.Component

@Component
class CreateOptionCommandHandler(
    private val optionService: OptionService
): CommandHandler<OptionDto, CreateOptionCommand> {
    override fun handle(command: CreateOptionCommand): OptionDto {
        return OptionMapper.mapToOptionDto(optionService.createOption(
            optionA = command.optionA,
            optionB = command.optionB,
            optionC = command.optionC,
            optionD = command.optionD,
            correctOption = command.correctOption,
            questionCode = command.questionCode
        ))
    }
}