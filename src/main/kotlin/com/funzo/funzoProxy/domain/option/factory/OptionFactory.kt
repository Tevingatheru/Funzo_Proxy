package com.funzo.funzoProxy.domain.option.factory

import com.funzo.funzoProxy.domain.option.Option
import com.funzo.funzoProxy.domain.option.factory.resource.OptionFactoryResource

interface OptionFactory  {
    fun create(createOption: OptionFactoryResource?): Option
}
