package com.funzo.funzoProxy.domain.question

import jakarta.persistence.*

@Entity
@Table(name = "true_or_false_answers")
data class TrueOrFalseQuestion(
    @Id
    override val id: Long? = 0,

    @ManyToOne
    @JoinColumn(name = "question_code")
    val question: Question? = null,

    @Column(name = "correct_option")
    val correctOption: Boolean? = null
): QuestionType(){
    //    constructor() : this(id = null, question = null, correctOption = null)
    constructor(question: Question, correctOption: Boolean, code: String) : this(
        correctOption = correctOption,
        question = question,
        )
}
