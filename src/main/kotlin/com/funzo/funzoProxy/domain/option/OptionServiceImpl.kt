package com.funzo.funzoProxy.domain.option

import com.funzo.funzoProxy.domain.option.factory.OptionFactory
import com.funzo.funzoProxy.domain.option.factory.resource.OptionFactoryResource
import com.funzo.funzoProxy.domain.option.strategy.MultipleChoiceStrategy
import com.funzo.funzoProxy.domain.option.strategy.OptionUpdateOperation
import com.funzo.funzoProxy.domain.option.strategy.TrueOrFalseStrategy
import com.funzo.funzoProxy.domain.option.strategy.UpdateStrategy
import com.funzo.funzoProxy.infrastructure.GenerateCodeService
import com.funzo.funzoProxy.infrastructure.jpa.OptionRepository
import com.funzo.funzoProxy.infrastructure.jpa.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class OptionServiceImpl (
    @Autowired private val optionRepository: OptionRepository,
    @Autowired private val questionRepository: QuestionRepository,
    private val generateCodeService: GenerateCodeService,
    private val optionFactory: OptionFactory,
    private var updateStrategy: UpdateStrategy
): OptionService {
    override fun createOption(
        optionA: String,
        optionB: String,
        optionC: String,
        optionD: String,
        correctOption: String,
        questionCode: String
    ): Option {
        return try {
            val option = optionFactory.create(
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

    override fun deleteByCode(code: String) {
        try {
            optionRepository.deleteByCode(code = code)
        } catch (e: Exception) {
            throw RuntimeException()
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
            optionRepository.getByQuestionCode(questionCode = questionCode)
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
            val option: Option = optionRepository.getByCode(code = code) ?: throw NotFoundException()
            when (option) {
                is MultipleChoiceOption -> {
                    updateStrategy = MultipleChoiceStrategy(option)
                }
                is TrueOrFalseOption -> {
                    updateStrategy = TrueOrFalseStrategy(option)
                }
            }

            optionRepository.saveAndFlush(updateStrategy.execute(
                OptionUpdateOperation(
                    correctOption = correctOption
                )
            ))
        } catch (e: Exception) {
            throw RuntimeException("Unable to update option. code: $code", e)
        }
    }
}
