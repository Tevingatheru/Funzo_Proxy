package com.funzo.funzoProxy.domain.option

import jakarta.persistence.*

@Entity
@Table(name = "multiple_choice_answers")
@PrimaryKeyJoinColumn(name = "option_id")
data class MultipleChoiceOption(

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
) : Option() {

}
