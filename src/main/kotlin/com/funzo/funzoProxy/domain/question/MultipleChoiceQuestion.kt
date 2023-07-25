package com.funzo.funzoProxy.domain.question

import jakarta.persistence.*

@Entity
@Table(name = "multiple_choice_answers")
data class MultipleChoiceQuestion(
    @Id
    override val id: Int?,

    @ManyToOne
    @JoinColumn(name = "question_code")
    val question: Question?,

    @Column(name = "option_a")
    val optionA: String?,

    @Column(name = "option_b")
    val optionB: String?,

    @Column(name = "option_c")
    val optionC: String?,

    @Column(name = "option_d")
    val optionD: String?,

    @Column(name = "correct_option")
    val correctOption: String?
) : QuestionType() {
    constructor() :
            this(null, null, null, null, null, null, null)
}
