package com.funzo.funzoProxy.domain.option.factory

import com.funzo.funzoProxy.domain.option.MultipleChoiceOption
import com.funzo.funzoProxy.domain.option.factory.resource.OptionFactoryResource
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


class MultipleChoiceOptionFactory : OptionFactory {
    override fun create(createOption: OptionFactoryResource?): MultipleChoiceOption {
        if (createOption != null) {
            return MultipleChoiceOption(
                optionA = createOption.optionA,
                optionB = createOption.optionB,
                optionC = createOption.optionC,
                optionD = createOption.optionD,
                correctOption = createOption.correctOption,
                code = createOption.code,
                question = createOption.question
            )
        }
        return MultipleChoiceOption()
    }
}
