package com.funzo.funzoProxy.domain.option.strategy

data class OptionUpdateOperation(
    val optionA: String? = null,
    val optionB: String? = null,
    val optionC: String? = null,
    val optionD: String? = null,
    val correctOption: String
) {

}
