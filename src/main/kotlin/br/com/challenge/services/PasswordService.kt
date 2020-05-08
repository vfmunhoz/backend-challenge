package br.com.challenge.services

import br.com.challenge.entities.PasswordStatus
import br.com.challenge.extensions.hasDigits
import br.com.challenge.extensions.hasLowerAndUpperCaseLetters
import br.com.challenge.extensions.hasSpecialChars
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class PasswordService {

    private val logger = LoggerFactory.getLogger(PasswordService::class.java)

    fun validatePassword(password: String): PasswordStatus {
        val validationErrorMessages = mutableListOf<String>()

        val trimPassword = password.trim()

        val isLengthValid = validateLength(trimPassword).also { verifyValidation(it, validationErrorMessages, PASSWORD_LENGTH_ERROR_MESSAGE) }
        val isCaseValid = trimPassword.hasLowerAndUpperCaseLetters().also { verifyValidation(it, validationErrorMessages, PASSWORD_LETTER_CASE_ERROR_MESSAGE) }
        val hasDigits = trimPassword.hasDigits().also { verifyValidation(it, validationErrorMessages, PASSWORD_NO_DIGIT_ERROR_MESSAGE) }
        val hasSpecialChars = trimPassword.hasSpecialChars().also { verifyValidation(it, validationErrorMessages, PASSWORD_NO_SPECIAL_CHAR_ERROR_MESSAGE) }

        this.logger.debug("password validation steps result: isLengthValid: [$isLengthValid], isCaseValid: [$isCaseValid], hasDigits: $hasDigits, hasSpecialChars: $hasSpecialChars")

        return PasswordStatus(
            isLengthValid && isCaseValid && hasDigits && hasSpecialChars,
            validationErrorMessages.toList()
        )
    }

    private fun validateLength(password: String): Boolean = password.length >= PASSWORD_MINIMUM_SIZE

    private fun verifyValidation(isValid: Boolean, errors: MutableCollection<String>, message: String) {
        if(!isValid) errors.add(message)
    }

    companion object {
        private const val PASSWORD_MINIMUM_SIZE = 9

        private const val PASSWORD_LENGTH_ERROR_MESSAGE = "Password must have at least 9 characters"
        private const val PASSWORD_LETTER_CASE_ERROR_MESSAGE = "Password must have at least one lower and one upper case letters"
        private const val PASSWORD_NO_DIGIT_ERROR_MESSAGE = "Password must have at least one digit"
        private const val PASSWORD_NO_SPECIAL_CHAR_ERROR_MESSAGE = "Password must have at least one special char"
    }
}
