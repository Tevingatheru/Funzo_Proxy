package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.bus.AddUserDetailsCommand
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserType
import com.funzo.funzoProxy.infrastructure.GenerateCodeService
import com.funzo.funzoProxy.infrastructure.UserRepository
import com.funzo.funzoProxy.infrastructure.UserRepositoryImpl
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.InjectMocks

import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks
import java.util.stream.Stream

class AddUserDetailsCommandHandlerTest {
    private val addUserDetailsCommand: AddUserDetailsCommand = AddUserDetailsCommand(UserType.STUDENT.toString(), "email")

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var addUserDetailsCommandHandler: AddUserDetailsCommandHandler


    @Mock
    private lateinit var generateCodeService: GenerateCodeService


    @BeforeEach
    fun setUp() {
        openMocks(this)
        addUserDetailsCommandHandler = AddUserDetailsCommandHandler(
            userRepositoryImpl = userRepository,
            generateCodeService = generateCodeService)

        `when`(generateCodeService.generateCodeWithLength(anyInt())).thenReturn("1234567")
        `when`(userRepository.save(any()))
            .thenReturn(User(1, "CODE", UserType.STUDENT, "email"))
    }

    @Test
    fun shouldHandleAddUserDetailsCommand() {
        val user = addUserDetailsCommandHandler.handle(addUserDetailsCommand)
        assertThat(user.id).isNotNull()
        verify(generateCodeService).generateCodeWithLength(7)
        verify(userRepository).save(any(User::class.java))
    }
}