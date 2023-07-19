package com.funzo.funzoProxy.infrastructure

import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UserRepositoryImplTest
{
    @Autowired
    private lateinit var userRepository: UserRepository

    private lateinit var user: User
    private lateinit var userRepositoryService: UserRepositoryImpl

    @BeforeEach
    fun setUp() {
        user = User( "CODE01", UserType.STUDENT, "tg@test.com")
        userRepositoryService = UserRepositoryImpl(userRepository)
    }

    @Test
    fun shouldFindByCode() {
        val savedUser = userRepository.save(user)
        assertThat(savedUser.id).isNotNull()
        val foundUser = userRepositoryService.findByCode(savedUser.code)
        assertThat(foundUser.code).isEqualTo(savedUser.code)
    }

    @Test
    fun shouldSaveUserSuccessfully() {
        val savedUser = userRepositoryService.save(user)
        assertThat(savedUser.id).isNotNull()
    }

    @Test
    fun shouldDeleteByCode() {
        val savedUser = userRepository.save(user)
        assertThat(savedUser.id).isNotNull()
        val foundUser = userRepositoryService.deleteByCode(savedUser.code)
        assertThat(foundUser).isEqualTo("OK")
        val allUsers = userRepository.findAll()
        assertThat(allUsers.isEmpty()).isTrue()
    }
}