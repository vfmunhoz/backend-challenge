package br.com.challenge.extensions

import br.com.challenge.entities.PasswordValidationResponse
import br.com.challenge.entities.PasswordStatus

fun PasswordStatus.toPasswordResponse(): PasswordValidationResponse = PasswordValidationResponse(
    isValid = first,
    validationErrors = second
)
