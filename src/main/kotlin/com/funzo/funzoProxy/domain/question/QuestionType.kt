package com.funzo.funzoProxy.domain.question

import jakarta.persistence.*


private const val TRUE_OR_FALSE = "true_or_false"

private const val MULTIPLE_CHOICE = "multiple_choice"

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class QuestionType(
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    open val id: Long? = 0,
    @Column(name = "code")
    open val code: String? = null
) {
    constructor(code: String): this(code = code, id = null)

    companion object {
        /**
         * This method check if the input string matches expected question type.
         */
        fun find(questionType: String): QuestionType {
            return when (questionType) {
                TRUE_OR_FALSE -> TrueOrFalseQuestion()
                MULTIPLE_CHOICE -> MultipleChoiceQuestion()
                else -> throw IllegalArgumentException("Invalid question type: $questionType")
            }
        }

        fun generateQuestionType(
            questionType: QuestionType,
            correctOption: String,
            question: Question,
            optionA: String?,
            optionB: String?,
            optionC: String?,
            optionD: String?,
            code: String,
        ): QuestionType {
            if (questionType is MultipleChoiceQuestion) {
                return createMCQ(code, optionA, optionB, optionC, optionD, question, correctOption)
            } else if (questionType is TrueOrFalseQuestion) {
                return createTFQ(question, correctOption, code)
            } else {
                throw IllegalArgumentException("Invalid question type")
            }
        }

        private fun createTFQ(questionText: Question, correctOption: String, code: String): TrueOrFalseQuestion {
            return TrueOrFalseQuestion(
                question = questionText,
                correctOption = correctOption.toBoolean(),
                code = code,
            )
        }

        private fun createMCQ(
            optionA: String?,
            optionB: String?,
            optionC: String?,
            optionD: String?,
            question: Question,
            correctOption: String
        ) : MultipleChoiceQuestion {
            return MultipleChoiceQuestion(
                id = null,
                optionA = optionA,
                optionB = optionB,
                optionC = optionC,
                optionD = optionD,
                question = question,
                correctOption = correctOption,
            )
        }
    }
}
