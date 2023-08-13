package com.funzo.funzoProxy.domain.option

import com.funzo.funzoProxy.domain.question.Question
import jakarta.persistence.*

@Entity
@Table(name = "multiple_choice_options")
@PrimaryKeyJoinColumn(name = "option_id")
data class MultipleChoiceOption(

    @Column(name = "option_a")
    var optionA: String? = null,

    @Column(name = "option_b")
    var optionB: String? = null,

    @Column(name = "option_c")
    var optionC: String? = null,

    @Column(name = "option_d")
    var optionD: String? = null,

    @Column(name = "correct_option")
    var correctOption: String? = null,

    ) : Option() {
    constructor(
        optionA: String?,
        optionB: String?,
        optionC: String?,
        optionD: String?,
        correctOption: String?,
        code: String?,
        question: Question?
    ) : this(optionA, optionB, optionC, optionD, correctOption) {
        this.code = code
        this.question = question
        this.id = 0
    }
}
