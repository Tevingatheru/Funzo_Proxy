package com.funzo.funzoProxy.domain.option.factory

import com.funzo.funzoProxy.domain.option.TrueOrFalseOption
import com.funzo.funzoProxy.domain.option.factory.resource.OptionFactoryResource

class TrueOrFalseOptionFactory : OptionFactory {
    override fun create(createOption: OptionFactoryResource?): TrueOrFalseOption {
        if (createOption != null) {
            return TrueOrFalseOption(
                correctOption = createOption.correctOption.toBoolean(),
                code = createOption.code,
                question = createOption.question
            )
        }
        return TrueOrFalseOption()
    }
}
