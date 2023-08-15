package com.funzo.funzoProxy

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.option.MultipleChoiceOption
import com.funzo.funzoProxy.domain.option.Option
import com.funzo.funzoProxy.domain.option.TrueOrFalseOption
import com.funzo.funzoProxy.domain.result.Result
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserType
import io.github.serpro69.kfaker.Faker
import kotlin.random.Random

object TestDataUtil {

    val faker = Faker()

    object ResultData {
        const val score = 0.0
        const val resultCode = "R01"
        const val userCode = "U01"
        val student = User(id = 1L, code = userCode, type = UserType.STUDENT, email = "student@emil.com")
        val exam = Exam(level = 1)
        val result = Result(
            id = 1L,
            exam = exam,
            student = student,
            code = resultCode,
            score = score,
            attemptNo = 0
        )
        const val examCode = "E01"
    }
    object OptionData {

        fun randomExam() = Exam(level = faker.random.nextInt(), code = randomCode(), subject = Subject())

        fun randomOption(): Option {
            return  if (Random.nextBoolean()) TrueOrFalseOption() else MultipleChoiceOption()
        }

        fun randomCode(): String {
            return faker.code.toString()
        }

        fun randomOptionText() = faker.starTrek.character()
        fun randomBooleanOptionText(): String {
            return Faker().random.nextBoolean().toString()
        }
    }
}
