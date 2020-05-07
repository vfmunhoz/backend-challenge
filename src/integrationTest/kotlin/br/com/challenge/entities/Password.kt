package br.com.challenge.entities

data class PasswordValidationRequest(
    val password: String
)

data class PasswordValidationResponse(
    val isValid: Boolean,
    val validationErrors: List<String>?
)
