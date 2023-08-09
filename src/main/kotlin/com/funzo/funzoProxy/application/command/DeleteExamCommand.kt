package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.domain.exam.DeleteExamCommandDto

class DeleteExamCommand(val code: String): Command<DeleteExamCommandDto> {

}
