package br.com.challenge.services

import br.com.challenge.entities.PasswordStatus
import javax.inject.Singleton

@Singleton
class PasswordService {

    fun validatePassword(password: String): PasswordStatus {
        val validationErrorMessages = mutableListOf<String>()
        val (isLengthValid, lengthValidationMessage) = validateLength(password)

        if(lengthValidationMessage != null) validationErrorMessages.add(lengthValidationMessage)

        return PasswordStatus(
                isLengthValid,
                validationErrorMessages.toList()
        )
    }

    private fun validateLength(password: String): Pair<Boolean, String?> =
            if(password.length >= PASSWORD_MINIMUM_SIZE) {
                true to null
            } else {
                false to "Password has less than 9 characters"
            }

    companion object {
        private const val PASSWORD_MINIMUM_SIZE = 9
    }
}
