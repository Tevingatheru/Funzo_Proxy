package com.funzo.funzoProxy.domain.option

import com.funzo.funzoProxy.domain.option.factory.MultipleChoiceOptionFactory
import com.funzo.funzoProxy.domain.option.factory.OptionFactory
import com.funzo.funzoProxy.domain.option.factory.TrueOrFalseOptionFactory
import com.funzo.funzoProxy.domain.option.factory.resource.OptionFactoryResource
import com.funzo.funzoProxy.domain.option.strategy.MultipleChoiceStrategy
import com.funzo.funzoProxy.domain.option.strategy.OptionUpdateOperation
import com.funzo.funzoProxy.domain.option.strategy.TrueOrFalseStrategy
import com.funzo.funzoProxy.domain.option.strategy.UpdateStrategy
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.infrastructure.GenerateCodeService
import com.funzo.funzoProxy.infrastructure.jpa.OptionRepository
import com.funzo.funzoProxy.infrastructure.jpa.QuestionRepository
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class OptionServiceImpl (
    @Autowired private val optionRepository: OptionRepository,
    @Autowired private val questionRepository: QuestionRepository,
    private val generateCodeService: GenerateCodeService,
    @Autowired(required = false)
    @Qualifier("optionFactory")
    private var optionFactory: OptionFactory? = null,
    @Autowired(required = false)
    @Qualifier("updateStrategy")
    private var updateStrategy: UpdateStrategy? = null
): OptionService {
    override fun createOption(
        optionA: String?,
        optionB: String?,
        optionC: String?,
        optionD: String?,
        correctOption: String,
        questionCode: String
    ): Option {
        return try {
            optionFactory = if (isTrueFalseOption(optionA, optionB, optionC, optionD)) {
                TrueOrFalseOptionFactory()
            } else {
                MultipleChoiceOptionFactory()
            }
            val option = optionFactory!!.create(
                OptionFactoryResource(
                    optionA = optionA,
                    optionB = optionB,
                    optionC = optionC,
                    optionD = optionD,
                    correctOption = correctOption,
                    code = generateCodeService.generateCodeWithLength(7),
                    question = questionRepository.findByCode(code = questionCode)
                )
            )

            optionRepository.saveAndFlush(option)
        } catch (e: Exception) {
            throw RuntimeException("Unable to create an option.", e)
        }
    }

    private fun isTrueFalseOption(
        optionA: String?,
        optionB: String?,
        optionC: String?,
        optionD: String?
    ) = optionA == null && optionB == null && optionC == null &&
            optionD == null

    override fun deleteByCode(code: String) {
        try {
            val option = this.getByCode(code)
            when (option) {
                is MultipleChoiceOption -> {
                    LoggerUtils.log(
                        level = LogLevel.INFO,
                        message = "Deleting MultipleChoiceOption by id. id: ${option.id}",
                        className = this::class.java
                    )
                    optionRepository.deleteMultipleChoiceOptionId(option.id!!)
                }
                is TrueOrFalseOption -> {
                    LoggerUtils.log(
                        level = LogLevel.INFO,
                        message = "Deleting TrueOrFalseOptionByOption by id. id: ${option.id}",
                        className = this::class.java
                    )
                    optionRepository.deleteTrueOrFalseOptionByOptionId(option.id!!)
                }
            }
            option.let {
                optionRepository.deleteByCode(it.code!!)
            }
        } catch (e: Exception) {
            throw RuntimeException("Unable to delete option. code: $code", e)
        }
    }

    override fun getByCode(code: String): Option {
        return try {
            optionRepository.getByCode(code = code)
        } catch (e: Exception) {
            throw RuntimeException("Unable to get option by code: code: $code", e)
        }
    }

    override fun getByQuestionCode(questionCode: String): Option {
        return try {
            optionRepository.getByQuestionCode(questionCode = questionCode).first()
        } catch (e: NoSuchElementException) {
            throw NotFoundException()
        } catch (e: Exception) {
            throw RuntimeException("Unable to get option by question code. questionCode: $questionCode", e)
        }
    }

    override fun modifyOption(
        code: String,
        optionA: String?,
        optionB: String?,
        optionC: String?,
        optionD: String?,
        correctOption: String
    ): Option {
        return try {
            when (val option: Option = optionRepository.getByCode(code = code)) {
                is MultipleChoiceOption -> {
                    updateStrategy = MultipleChoiceStrategy(option)
                }
                is TrueOrFalseOption -> {
                    updateStrategy = TrueOrFalseStrategy(option)
                }
            }

            optionRepository.saveAndFlush(updateStrategy!!.execute(
                OptionUpdateOperation(
                    correctOption = correctOption,
                    optionA = optionA,
                    optionB = optionB,
                    optionC = optionC,
                    optionD = optionD,
                )
            ))
        } catch (e: Exception) {
            throw RuntimeException("Unable to update option. code: $code", e)
        }
    }

    override fun findAll(): List<Option> {
        return try {
            optionRepository.findAll()
        } catch (e: Exception) {
            throw RuntimeException("Unable to find all options", e)
        }
    }

}
