package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.DeleteResultByCodeCommand
import com.funzo.funzoProxy.domain.result.ResultService
import org.springframework.stereotype.Component

@Component
class DeleteResultByCodeCommandHandler(
    private val resultService: ResultService
): CommandHandler<Unit, DeleteResultByCodeCommand> {
    override fun handle(command: DeleteResultByCodeCommand): Unit {
        return resultService.deleteByCode(code = command.code)
    }
}
