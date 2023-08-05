package com.funzo.funzoProxy.domain.question

import jakarta.persistence.*

@Entity
@Table(name = "multiple_choice_answers")
data class MultipleChoiceQuestion(
    @Id
    override val id: Long? = 0,

    @ManyToOne
    @JoinColumn(name = "question_code")
    val question: Question? = null,

    @Column(name = "option_a")
    val optionA: String? = null,

    @Column(name = "option_b")
    val optionB: String? = null,

    @Column(name = "option_c")
    val optionC: String? = null,

    @Column(name = "option_d")
    val optionD: String? = null,

    @Column(name = "correct_option")
    val correctOption: String? = null
) : QuestionType() {
//    constructor() :
//            this(null, null, null, null, null, null, null)
}
