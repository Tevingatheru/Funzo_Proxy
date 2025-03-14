package com.funzo.funzoProxy.domain.option

interface OptionService {
    fun createOption(
        optionA: String?,
        optionB: String?,
        optionC: String?,
        optionD: String?,
        correctOption: String,
        questionCode: String
    ): Option

    fun deleteByCode(code: String)

    fun getByCode(code: String) : Option

    fun getByQuestionCode(questionCode: String): Option

    fun modifyOption(
        code: String,
        optionA: String?,
        optionB: String?,
        optionC: String?,
        optionD: String?,
        correctOption: String
    ): Option

    fun findAll(): List<Option>
}
