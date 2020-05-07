package br.com.challenge.extensions

import br.com.challenge.controllers.entities.PasswordResponse
import br.com.challenge.entities.PasswordStatus

fun PasswordStatus.toPasswordResponse(): PasswordResponse = PasswordResponse(
        isValid = first,
        validationErrors = second
)
