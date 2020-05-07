package br.com.challenge.services

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import javax.inject.Inject

@MicronautTest
class PasswordServiceTest {

    @Inject
    lateinit var passwordService: PasswordService

    @ParameterizedTest
    @ValueSource(strings = ["AbTp9!foo", "Jhdg*hsh1"])
    fun `should run successfully with a valid password`(password: String) {
        val (isValid, validationErrors) = passwordService.validatePassword(password)

        assertThat(isValid).isTrue()
        assertThat(validationErrors.isEmpty()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["AbTp9!fo", "kt1!"])
    fun `password must be invalid if length is smaller than 9`(password: String) {
        val (isValid, validationErrors) = passwordService.validatePassword(password)

        assertThat(isValid).isFalse()
        assertThat(validationErrors.isEmpty()).isFalse()
        assertThat(validationErrors.contains(PASSWORD_LENGTH_ERROR_MESSAGE)).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["abtp9!foo", "ABTP9!FOO" ])
    fun `password must be invalid if it doesn't have at least 1 capital letter and 1 lower case letter`(password: String) {
        val (isValid, validationErrors) = passwordService.validatePassword(password)

        assertThat(isValid).isFalse()
        assertThat(validationErrors.isEmpty()).isFalse()
        assertThat(validationErrors.contains(PASSWORD_LETTER_CASE_ERROR_MESSAGE)).isTrue()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR_MESSAGE = "Password has less than 9 characters"
        private const val PASSWORD_LETTER_CASE_ERROR_MESSAGE = "Password must have at least one lower and one upper case letters"
    }
}
