package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.command.bus.Command

class AddUserDetailsCommand (val userType: String, val email: String): Command {

}