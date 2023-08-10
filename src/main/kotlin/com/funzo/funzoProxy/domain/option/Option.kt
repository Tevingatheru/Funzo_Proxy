package com.funzo.funzoProxy.domain.option

import com.funzo.funzoProxy.domain.question.Question
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue(value = "option_code")
abstract class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    val id: Long? = 0

    @Column(name = "code", nullable = false)
    val code: String? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_code", referencedColumnName = "code", nullable = false)
    val question: Question? = null

    companion object {
        /**
         * This method check if the input string matches expected question type.
         */
        fun find(questionType: String): Option {
            return when (questionType) {
                OptionType.TRUE_OR_FALSE.optionTypeName -> TrueOrFalseOption()
                OptionType.MULTIPLE_CHOICE.optionTypeName -> MultipleChoiceOption()
                else -> throw IllegalArgumentException("Invalid question type: $questionType")
            }
        }
    }
}
