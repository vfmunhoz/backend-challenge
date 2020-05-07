package br.com.challenge.controllers.entities

data class PasswordRequest(
        val password: String
)

data class PasswordResponse(
        val isValid: Boolean,
        val validationErrors: List<String>
)
