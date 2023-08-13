package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.ModifyOptionCommand
import com.funzo.funzoProxy.application.mapper.OptionMapper
import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.infrastructure.dto.OptionDto
import org.springframework.stereotype.Component

@Component
class ModifyOptionCommandHandler(
    private val optionService: OptionService
): CommandHandler<OptionDto, ModifyOptionCommand> {
    override fun handle(command: ModifyOptionCommand): OptionDto {
        return OptionMapper.mapToOptionDto(
            optionService.modifyOption(code = command.code,
                optionA = command.optionA,
                optionB = command.optionB,
                optionC = command.optionC,
                optionD = command.optionD,
                correctOption = command.correctOption)
        )
    }
}
