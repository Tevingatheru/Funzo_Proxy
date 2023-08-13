package com.funzo.funzoProxy.domain.option.strategy

import com.funzo.funzoProxy.domain.option.Option

interface UpdateStrategy {
    fun execute(dto: OptionUpdateOperation): Option
}
