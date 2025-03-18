package com.funzo.funzoProxy.domain.option.strategy

import com.funzo.funzoProxy.domain.option.TrueOrFalseOption

class TrueOrFalseStrategy(private var trueOrFalseOption: TrueOrFalseOption) : UpdateStrategy {
    override fun execute(dto: OptionUpdateOperation): TrueOrFalseOption {
        val newCorrectOption = dto.correctOption.toBoolean()
        if (trueOrFalseOption.correctOption != newCorrectOption) {
            this.trueOrFalseOption.correctOption = newCorrectOption
        }

        return  this.trueOrFalseOption
    }
}