package com.funzo.funzoProxy.application.mapper

import com.funzo.funzoProxy.domain.option.MultipleChoiceOption
import com.funzo.funzoProxy.domain.option.Option
import com.funzo.funzoProxy.domain.option.TrueOrFalseOption
import com.funzo.funzoProxy.infrastructure.dto.OptionDto
import com.funzo.funzoProxy.infrastructure.dto.OptionListDto

object OptionMapper {
    fun mapToOptionDto(option: Option): OptionDto {
        when (option) {
            is MultipleChoiceOption -> return OptionDto(code = option.code,
                questionCode = option.question!!.code,
                optionA = option.optionA,
                optionB = option.optionB,
                optionC = option.optionC,
                optionD = option.optionD,
                correctOption = option.correctOption
                )
            is TrueOrFalseOption -> return OptionDto(
                code = option.code,
                questionCode = option.question!!.code,
                correctOption = option.correctOption.toString()
            )
            else -> throw IllegalArgumentException("Unable to find option.")
        }
    }

    fun mapToOptionListDto(optionList: List<Option>): OptionListDto {
        val optionListDto = OptionListDto()
        optionList.forEach {
            optionListDto.add(mapToOptionDto(it))
        }

        return optionListDto
    }
}