package com.funzo.Funzo_Proxy

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan


@Entity
@Table(name = "true_or_false_answers")
data class TrueOrFalseQuestion (
    @Id
    override val id: Int,

    @ManyToOne
    @JoinColumn(name = "question_code")
    val question: Question,

    @Column(name = "correct_option")
    val correctOption: Boolean
): QuestionType()
