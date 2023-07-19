package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UserServiceImplTest
{
    @Autowired
    private lateinit var userRepository: UserRepository

    private lateinit var user: User

    private lateinit var userServiceImpl: UserServiceImpl

    @BeforeEach
    fun setUp() {
        user = User( "CODE01", UserType.STUDENT, "tg@test.com")
        userServiceImpl = UserServiceImpl(userRepository)
    }

    @Test
    fun shouldFindUserByCode() {
        val savedUser = userRepository.save(user)
        assertThat(savedUser.id).isNotNull()
        val foundUser = userServiceImpl.findByCode(savedUser.code)
        assertThat(foundUser.code).isEqualTo(savedUser.code)
    }

    @Test
    fun shouldSaveUserSuccessfully() {
        val savedUser = userServiceImpl.save(user)
        assertThat(savedUser.id).isNotNull()
    }

    @Test
    fun shouldDeleteByCode() {
        val savedUser = userRepository.save(user)
        assertThat(savedUser.id).isNotNull()
        val foundUser = userServiceImpl.deleteByCode(savedUser.code)
        assertThat(foundUser).isEqualTo("OK")
        val allUsers = userRepository.findAll()
        assertThat(allUsers.isEmpty()).isTrue()
    }
}