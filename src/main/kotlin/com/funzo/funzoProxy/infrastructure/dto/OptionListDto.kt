package com.funzo.funzoProxy.infrastructure.dto

data class OptionListDto (
    val options: MutableList<OptionDto> = mutableListOf()
) {
    fun add(optionDto: OptionDto) {
        options.add(optionDto)
    }
}
