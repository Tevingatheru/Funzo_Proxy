package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.DeleteOptionByCodeCommand
import com.funzo.funzoProxy.domain.option.OptionService
import org.springframework.stereotype.Component

@Component
class DeleteOptionByCodeCommandHandler(
    private val optionService: OptionService
): CommandHandler<Unit, DeleteOptionByCodeCommand> {
    override fun handle(command: DeleteOptionByCodeCommand) {
        optionService.deleteByCode(code = command.code)
    }
}
