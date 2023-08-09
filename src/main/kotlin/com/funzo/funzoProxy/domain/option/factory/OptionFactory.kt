package com.funzo.funzoProxy.domain.option.factory

import com.funzo.funzoProxy.domain.option.Option

interface OptionFactory  {
    fun create(createOption: OptionResource): Option
}
