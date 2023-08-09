package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateSubjectCommand
import com.funzo.funzoProxy.domain.subject.SubjectServiceImpl
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest

import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import java.util.stream.Stream

class CreateSubjectCommandHandlerTest {

    companion object {
        const val name = "name"
        const val category = "category"
        const val description = "description"

        @JvmStatic
        fun provider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(CreateSubjectCommand("", "", ""), "category"),
                Arguments.of(CreateSubjectCommand("", "", category), "name"),
                Arguments.of(CreateSubjectCommand(name, "", category), "description"),
            )
        }
    }

    @Mock
    private lateinit var subjectServiceImpl: SubjectServiceImpl

    @InjectMocks
    private lateinit var createSubjectCommandHandler: CreateSubjectCommandHandler

    @BeforeEach
    fun setUp() {
        openMocks(this)
        createSubjectCommandHandler = CreateSubjectCommandHandler(subjectServiceImpl = subjectServiceImpl)
    }

    @ParameterizedTest
    @MethodSource("provider")
    fun shouldThrowExceptionWithInvalidInput(createSubjectCommand: CreateSubjectCommand, message : String) {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                createSubjectCommandHandler.handle(createSubjectCommand)
                verify(subjectServiceImpl, times(0))
                    .createSubject(any(CreateSubjectCommand::class.java))
            }.withMessageContaining(message)
    }

}
