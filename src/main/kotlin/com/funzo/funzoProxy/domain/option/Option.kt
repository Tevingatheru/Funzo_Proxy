package com.funzo.funzoProxy.domain.option

import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.infrastructure.dto.OptionDto
import jakarta.persistence.*
import lombok.AccessLevel
import lombok.NoArgsConstructor

@Entity
@Table(name = "options")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue(value = "option_code")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class Option (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "code", nullable = false, unique = true)
    var code: String? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_code", referencedColumnName = "code", nullable = false)
    var question: Question? = null
) {
    fun getOptionTypeName(): String {
        when (this) {
            is MultipleChoiceOption -> return OptionType.MULTIPLE_CHOICE.optionTypeName
            is TrueOrFalseOption -> return OptionType.TRUE_OR_FALSE.optionTypeName
            else -> throw IllegalArgumentException("Unable to find option.")
        }
    }
}
