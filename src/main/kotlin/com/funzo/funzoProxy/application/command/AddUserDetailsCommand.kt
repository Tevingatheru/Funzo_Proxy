package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.infrastructure.dto.AddUserDetailsDto


class AddUserDetailsCommand (val userType: String, val email: String)
    : Command<AddUserDetailsDto>
