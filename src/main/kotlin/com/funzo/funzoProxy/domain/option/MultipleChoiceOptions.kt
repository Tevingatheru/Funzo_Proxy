package com.funzo.funzoProxy.domain.option

import jakarta.persistence.*

@Entity
@Table(name = "multiple_choice_answers")
@PrimaryKeyJoinColumn(name = "option_id")
data class MultipleChoiceOptions(

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
) : Option() {
    constructor() :
            this(optionA = null, optionB = null, optionC = null, optionD = null, correctOption = null)
}
