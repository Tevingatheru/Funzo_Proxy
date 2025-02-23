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
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_code", referencedColumnName = "code")
    var subject: Subject? = null,

    @Column(unique = true, name = "code", nullable = false)
    val code: String? = null,

    @OneToMany(mappedBy = "exam", cascade = [CascadeType.ALL],
        orphanRemoval = true, fetch = FetchType.LAZY)
    val questions: MutableList<Question>? = mutableListOf()
) {
    constructor(code: String, subject: Subject)
            : this(null, subject, code, null)

}
