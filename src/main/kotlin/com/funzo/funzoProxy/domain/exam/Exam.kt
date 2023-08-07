package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.domain.subject.Subject
import jakarta.persistence.*
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException


@Entity
@Table(name = "exams")
data class Exam(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @ManyToOne
    @JoinColumn(name = "subject_code", referencedColumnName = "code")
    var subject: Subject?,

    @Column(unique = true, name = "code")
    val code: String?,

    @Column
    val level: Int?,

    @OneToMany(mappedBy = "exam", cascade = [CascadeType.ALL], orphanRemoval = true)
    val questions: MutableList<Question>? = mutableListOf()
) {
    constructor(level: Int, code: String) : this(null, null, code, level,null)
    constructor(level: Int) : this(null, null, null, level, null)

    constructor(): this(null, null, null, null, null)

    fun addQuestion(question: Question) {
        questions?.add(question)
    }

    fun findQuestionByCode(code: String): Question {
        var question: Question = Question()
        for (iteration : Question in questions!!) {
            if(iteration.code == code) {
                question = iteration
            }
        }

        if (question == null) {
            throw NotFoundException()
        }

        return question
    }
}
