package com.funzo.funzoProxy.domain.option.strategy

import com.funzo.funzoProxy.domain.option.Option
import com.funzo.funzoProxy.domain.option.TrueOrFalseOption
import org.springframework.stereotype.Component

@Component
class TrueOrFalseStrategy(var trueOrFalseOption: TrueOrFalseOption) : UpdateStrategy {
    override fun execute(dto: OptionUpdateOperation): Option {
        val newCorrectOption = dto.correctOption.toBoolean()
        if (trueOrFalseOption.correctOption != newCorrectOption) {
            this.trueOrFalseOption.correctOption = newCorrectOption
        }

        return  this.trueOrFalseOption
    }
}