package com.funzo.funzoProxy.domain.option.factory

import com.funzo.funzoProxy.domain.option.MultipleChoiceOption

class MultipleChoiceOptionFactory : OptionFactory {
    override fun create(createOption: OptionResource): MultipleChoiceOption {
        return MultipleChoiceOption(
            optionA = createOption.optionA,
            optionB = createOption.optionB,
            optionC = createOption.optionC,
            optionD = createOption.optionD,
            correctOption = createOption.correctOption
        )
    }
}
