package com.funzo.funzoProxy.infrastructure

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations.openMocks
import java.util.stream.Stream

class GenerateCodeServiceImplTest {

    @InjectMocks
    private val generateCodeService: GenerateCodeServiceImpl = GenerateCodeServiceImpl()

    companion object {
        @JvmStatic
        private fun codeLengthProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(6),
                Arguments.of(12)
            )
        }
    }

    @BeforeEach
    fun setUp() {
        openMocks(this)
    }

    @ParameterizedTest(name = "generateCodeWithLength of {0}")
    @MethodSource("com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImplTest#codeLengthProvider")
    fun shouldGenerateCodeWithLength(length: Int) {
        val code = generateCodeService.generateCodeWithLength(length)
        assertThat(code.length).isEqualTo(length)
    }
}
