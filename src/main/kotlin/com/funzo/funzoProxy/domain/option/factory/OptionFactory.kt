package com.funzo.funzoProxy.domain.option.factory

import com.funzo.funzoProxy.domain.option.Option
import com.funzo.funzoProxy.domain.option.factory.resource.OptionResource

interface OptionFactory  {
    fun create(createOption: OptionResource): Option
}
