package com.funzo.funzoProxy.domain.option

import jakarta.persistence.*

@Entity
@Table(name = "true_or_false_answers")
@PrimaryKeyJoinColumn(name = "option_id")
data class TrueOrFalseOption(

    @Column(name = "correct_option")
    val correctOption: Boolean? = null
): Option(){


}
