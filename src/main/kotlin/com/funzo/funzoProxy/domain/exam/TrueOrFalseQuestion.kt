package com.funzo.funzoProxy.domain.exam

import jakarta.persistence.*

@Entity
@Table(name = "true_or_false_answers")
data class TrueOrFalseQuestion(
    @Id
    override val id: Int? = 0,

    @ManyToOne
    @JoinColumn(name = "question_code")
    val question: Question?,

    @Column(name = "correct_option")
    val correctOption: Boolean?
): QuestionType(){
    constructor() : this(null, null, null)

}
