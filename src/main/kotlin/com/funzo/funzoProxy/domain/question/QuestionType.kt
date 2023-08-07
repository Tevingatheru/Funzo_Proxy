package com.funzo.funzoProxy.domain.question

import jakarta.persistence.*


private const val TRUE_OR_FALSE = "true_or_false"

private const val MULTIPLE_CHOICE = "multiple_choice"

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
                TRUE_OR_FALSE -> TrueOrFalseQuestion()
                MULTIPLE_CHOICE -> MultipleChoiceQuestion()
                else -> throw IllegalArgumentException("Invalid question type: $questionType")
            }
        }

        fun generateQuestionType(
            questionType: QuestionType,
            savedQuestion: Question
        ): Question {
            return when (questionType) {
                is MultipleChoiceQuestion -> {
                    val multipleChoiceQuestion = MultipleChoiceQuestion(
                        null,
                        savedQuestion,
                        questionType.optionA,
                        questionType.optionB,
                        questionType.optionC,
                        questionType.optionD,
                        questionType.correctOption
                    )
                    savedQuestion.type = multipleChoiceQuestion
                    return savedQuestion
                }

                is TrueOrFalseQuestion -> {
                    val trueOrFalseQuestion = TrueOrFalseQuestion(
                        null,
                        savedQuestion,
                        questionType.correctOption
                    )
                    savedQuestion.type = trueOrFalseQuestion
                    return savedQuestion
                }

                else -> {
                    throw IllegalArgumentException("Invalid question type")
                }
            }
        }
    }
}
