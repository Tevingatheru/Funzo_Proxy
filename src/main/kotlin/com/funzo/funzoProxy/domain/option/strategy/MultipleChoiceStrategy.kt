package com.funzo.funzoProxy.domain.option.strategy

import com.funzo.funzoProxy.domain.option.MultipleChoiceOption
import com.funzo.funzoProxy.domain.option.Option
import org.springframework.stereotype.Component

@Component
class MultipleChoiceStrategy(private var multipleChoiceOption: MultipleChoiceOption) : UpdateStrategy {

    override fun execute(dto: OptionUpdateOperation): Option {
        if(!this.multipleChoiceOption.optionA.equals(dto.optionA)) {
            multipleChoiceOption.optionA = dto.optionA
        }
        if(!this.multipleChoiceOption.optionB.equals(dto.optionB)) {
            multipleChoiceOption.optionB = dto.optionB
        }
        if(!this.multipleChoiceOption.optionC.equals(dto.optionC)) {
            multipleChoiceOption.optionC = dto.optionC
        }
        if(!this.multipleChoiceOption.optionD.equals(dto.optionD)) {
            multipleChoiceOption.optionD = dto.optionD
        }
        if(!this.multipleChoiceOption.correctOption.equals(dto.correctOption)) {
            multipleChoiceOption.correctOption = dto.correctOption
        }
        return multipleChoiceOption
    }
}