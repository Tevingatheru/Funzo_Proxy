package com.funzo.funzoProxy.domain.option

import com.funzo.funzoProxy.domain.question.Question
import jakarta.persistence.*

@Entity
@Table(name = "true_or_false_options")
@PrimaryKeyJoinColumn(name = "option_id")
data class TrueOrFalseOption(

    @Column(name = "correct_option")
    var correctOption: Boolean? = null
): Option(){
    constructor(correctOption: Boolean, code: String?, question: Question)
            : this(correctOption = correctOption) {
        this.code = code
        this.question = question
        this.id = 0
    }
}
