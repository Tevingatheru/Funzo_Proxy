package com.funzo.funzoProxy.domain.exam

import jakarta.persistence.*


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    open val id: Int? = 0

    @Column(name = "code")
    open val code: String? = null

    companion object {
        /**
         * This method check if the input string matches expected question type.
         */
        fun find(questionType: String): QuestionType {
            return when (questionType) {
                QuestionTypeEnum.TRUE_OR_FALSE.type -> TrueOrFalseQuestion()
                QuestionTypeEnum.MCQ.type -> MultipleChoiceQuestion()
                else -> throw IllegalArgumentException("Invalid question type: $questionType")
            }
        }
    }
}

enum class QuestionTypeEnum(val type: String) {
    TRUE_OR_FALSE("true_or_false"),
    MCQ("multiple_choice")
}