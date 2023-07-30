package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.AddUserDetailsCommand
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.domain.user.UserType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.InjectMocks

import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks

class AddUserCommandHandlerTest {
    private val addUserDetailsCommand: AddUserDetailsCommand = AddUserDetailsCommand(UserType.STUDENT.toString(), "email")

    @Mock
    private lateinit var userService: UserService

    @InjectMocks
    private lateinit var addUserCommandHandler: AddUserCommandHandler


    @BeforeEach
    fun setUp() {
        openMocks(this)
        addUserCommandHandler = AddUserCommandHandler(
            userService = userService)

        `when`(userService.save(anyString(), anyString()))
            .thenReturn(User(1, "CODE", UserType.STUDENT, "email"))
    }

    @Test
    fun shouldHandleAddUserDetailsCommand() {
        val user = addUserCommandHandler.handle(addUserDetailsCommand)
        assertThat(user.userCode).isNotNull()
        verify(userService).save(anyString(), anyString())
    }
}