package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateResultCommand
import com.funzo.funzoProxy.application.mapper.ResultDtoMapper
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.CreateResultDto
import org.springframework.stereotype.Component

@Component
class CreateResultCommandHandler(
    private val resultService: ResultService
): CommandHandler<CreateResultDto, CreateResultCommand> {
    override fun handle(command: CreateResultCommand): CreateResultDto {
        return ResultDtoMapper
            .mapToCreateResultDto(
                resultService.createResult(command.examCode, command.userCode, command.score))
    }
}
