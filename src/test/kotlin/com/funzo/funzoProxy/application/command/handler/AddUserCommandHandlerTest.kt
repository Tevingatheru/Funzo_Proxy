package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.AddUserDetailsCommand
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserType
import com.funzo.funzoProxy.infrastructure.GenerateCodeService
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.InjectMocks

import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks

class AddUserCommandHandlerTest {
    private val addUserDetailsCommand: AddUserDetailsCommand = AddUserDetailsCommand(UserType.STUDENT.toString(), "email")

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var addUserCommandHandler: AddUserCommandHandler


    @Mock
    private lateinit var generateCodeService: GenerateCodeService


    @BeforeEach
    fun setUp() {
        openMocks(this)
        addUserCommandHandler = AddUserCommandHandler(
            userRepositoryImpl = userRepository,
            generateCodeService = generateCodeService)

        `when`(generateCodeService.generateCodeWithLength(anyInt())).thenReturn("1234567")
        `when`(userRepository.save(any()))
            .thenReturn(User(1, "CODE", UserType.STUDENT, "email"))
    }

    @Test
    fun shouldHandleAddUserDetailsCommand() {
        val user = addUserCommandHandler.handle(addUserDetailsCommand)
        assertThat(user.id).isNotNull()
        verify(generateCodeService).generateCodeWithLength(7)
        verify(userRepository).save(any(User::class.java))
    }
}