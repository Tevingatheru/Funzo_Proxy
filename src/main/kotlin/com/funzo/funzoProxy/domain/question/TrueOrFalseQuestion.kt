package com.funzo.funzoProxy.domain.question

import jakarta.persistence.*

@Entity
@Table(name = "true_or_false_answers")
data class TrueOrFalseQuestion(
    @Id
    override val id: Long? = 0,


    @Column(name = "correct_option")
    val correctOption: Boolean?
): QuestionType(){
    constructor() : this(null, null)

}
