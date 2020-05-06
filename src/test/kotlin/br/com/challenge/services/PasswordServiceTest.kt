package br.com.challenge.services

import assertk.assertThat
import assertk.assertions.isEqualTo
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
        val (isValid, message) = passwordService.validatePassword(password)

        assertThat(isValid).isTrue()
        assertThat(message).isEqualTo("Password $password is valid!")
    }

    @ParameterizedTest
    @ValueSource(strings = ["AbTp9!fo"])
    fun `password must be invalid if length is smaller than 9`(password: String) {
        val (isValid, message) = passwordService.validatePassword(password)

        assertThat(isValid).isFalse()
        assertThat(message).isEqualTo("Password has less than 9 characters")
    }
}
