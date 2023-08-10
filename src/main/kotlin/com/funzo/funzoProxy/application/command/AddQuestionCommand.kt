package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.controller.response.AddQuestionCommandResponse

class AddQuestionCommand(val examCode: String, val image: String?, val questionText: String) : Command<AddQuestionCommandResponse>{

}
