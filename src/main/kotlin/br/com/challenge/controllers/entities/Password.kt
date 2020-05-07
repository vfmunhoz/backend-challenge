package br.com.challenge.controllers.entities

data class PasswordValidationRequest(
    val password: String
)

data class PasswordValidationResponse(
    val isValid: Boolean,
    val validationErrors: List<String>
)

data class PasswordValidationErrorResponse(
    val message: String
)
