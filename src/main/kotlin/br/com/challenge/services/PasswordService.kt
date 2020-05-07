package br.com.challenge.services

import br.com.challenge.entities.PasswordStatus
import javax.inject.Singleton

@Singleton
class PasswordService {

    fun validatePassword(password: String): PasswordStatus {
        val validationErrorMessages = mutableListOf<String>()

        val (isLengthValid, lengthValidationMessage) = validateLength(password)
        val (isCaseValid, caseValidationMessage) = validatePasswordCase(password)
        val (hasDigits, digitsValidationMessage) = validatePasswordHasDigits(password)
        val (hasSpecialChars, specialCharValidationMessage) = validatePasswordHasSpecialChars(password)

        if(lengthValidationMessage != null) validationErrorMessages.add(lengthValidationMessage)
        if(caseValidationMessage != null) validationErrorMessages.add(caseValidationMessage)
        if(digitsValidationMessage != null) validationErrorMessages.add(digitsValidationMessage)
        if(specialCharValidationMessage != null) validationErrorMessages.add(specialCharValidationMessage)

        return PasswordStatus(
                isLengthValid && isCaseValid && hasDigits && hasSpecialChars,
                validationErrorMessages.toList()
        )
    }

    private fun validateLength(password: String): Pair<Boolean, String?> =
            if(password.length >= PASSWORD_MINIMUM_SIZE) {
                true to null
            } else {
                false to "Password has less than 9 characters"
            }

    private fun validatePasswordCase(password: String): Pair<Boolean, String?> {
        return if(PASSWORD_PATTERN_REGEX.toRegex().matches(password)) {
            true to null
        } else {
            false to "Password must have at least one lower and one upper case letters"
        }
    }

    private fun validatePasswordHasDigits(password: String): Pair<Boolean, String?> {
        return if(PASSWORD_HAS_DIGITS_REGEX.toRegex().matches(password)) {
            true to null
        } else {
            false to "Password must have at least one digit"
        }
    }

    private fun validatePasswordHasSpecialChars(password: String): Pair<Boolean, String?> {
        return if(PASSWORD_HAS_SPECIAL_CHARS_REGEX.toRegex().matches(password)) {
            true to null
        } else {
            false to "Password must have at least one special char"
        }
    }

    companion object {
        private const val PASSWORD_MINIMUM_SIZE = 9
        private const val PASSWORD_PATTERN_REGEX = "((?=.*[A-Z])(?=.*[a-z])).*"
        private const val PASSWORD_HAS_DIGITS_REGEX = "(?=.*\\d).*"
        private const val PASSWORD_HAS_SPECIAL_CHARS_REGEX = "(?=.*[\\W]).*"
    }
}
