package com.funzo.funzoProxy.domain.subject

import com.funzo.funzoProxy.application.command.CreateSubjectCommand
import com.funzo.funzoProxy.infrastructure.jpa.SubjectRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks

class SubjectServiceImplTest {
    companion object {
        const val subjectName = "Math"
        const val subjectDescription = "addition, and subtraction"
        const val subjectCategory = "Science"
        private val createSubjectCommand: CreateSubjectCommand =
            CreateSubjectCommand(subjectName, subjectDescription, subjectCategory)

        const val subjectCode = "CODE"

    }

    @Mock
    private lateinit var subjectRepository: SubjectRepository

    @InjectMocks
    private lateinit var subjectServiceImpl: SubjectServiceImpl

    @BeforeEach
    fun setUp() {
        openMocks(this)
        subjectServiceImpl = SubjectServiceImpl(subjectRepository = subjectRepository)
        val returnSubject = Subject(1, subjectCode, subjectName, subjectDescription, subjectCategory)
        `when`(subjectRepository.save(any()))
            .thenReturn(returnSubject)
        `when`(subjectRepository.findByCode(anyString()))
            .thenReturn(returnSubject)
    }

    @Test
    fun shouldCallSaveWhenSavingSubject() {
        subjectServiceImpl.createSubject(createSubjectCommand = createSubjectCommand)
        verify(subjectRepository).save(any())
    }

    @Nested
    inner class WhenUpdatingSubjectDetails {

        @Test
        fun shouldFindSubject() {
            subjectServiceImpl.updateSubjectDetails(subjectCode)
            verify(subjectRepository).findByCode(anyString())
        }

        @Test
        fun shouldUpdateSubject() {
            subjectServiceImpl.updateSubjectDetails(subjectCode)
            verify(subjectRepository).save(any())
        }
    }

    @Test
    fun shouldCallDeleteSubjectByCode() {
        subjectServiceImpl.deleteSubjectByCode(subjectCode)
        verify(subjectRepository).deleteByCode(anyString())
    }
}