package com.funzo.funzoProxy.domain.option.factory

import com.funzo.funzoProxy.domain.option.TrueOrFalseOption
import com.funzo.funzoProxy.domain.option.factory.resource.OptionResource

class TrueOrFalseOptionFactory : OptionFactory {
    override fun create(createOption: OptionResource): TrueOrFalseOption {
        return TrueOrFalseOption(
            correctOption = createOption.correctOption.toBoolean()
        )
    }
}