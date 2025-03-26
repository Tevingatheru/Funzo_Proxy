package com.funzo.funzoProxy.infrastructure.dto

import com.funzo.funzoProxy.application.mapper.OptionMapper
import com.funzo.funzoProxy.domain.option.Option
import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils

data class ExamContentDto(
    private var examCode: String? = null,
    private var totalQuestions: Int? = null,
    private var questions: MutableList<QuestionContentDto> = mutableListOf()
) {
    fun setExamCode(examCode: String) {
        this.examCode = examCode
    }

    fun getExamCode() : String{
        return this.examCode!!
    }

    fun setTotalNumberOfQuestions(examQuestions: MutableList<Question>) {
        totalQuestions =  examQuestions.size
    }

    fun getTotalNumberOfQuestions() : Int {
        return totalQuestions!!
    }

    fun setQuestionsAndOptions(examQuestions: MutableList<Question>, optionServiceImpl : OptionService) {
        examQuestions.forEach {
            val option: Option? = try {
                optionServiceImpl.getByQuestionCode(it.code!!)
            } catch (e: Exception) {
                LoggerUtils.log(
                    level = LogLevel.INFO,
                    message = "Question has no option.",
                    className = this::class.java,
                    diagnosisMap = mapOf(Pair("examCode", examCode!!),
                        Pair("questionCode", it.code!!),
                        Pair("question", it.question!!))
                )
                null
            }
            val questionDto: QuestionContentDto = QuestionContentDto(
                examCode = it.exam!!.code!!,
                questionType = option?.getOptionTypeName(),
                text = it.question,
                code = it.code,
                option = option?.let { OptionMapper.mapToOptionDto(it) }
            )
            questions.add(questionDto)
        }
    }

    fun getQuestions(): List<QuestionContentDto> {
        return questions
    }
}
