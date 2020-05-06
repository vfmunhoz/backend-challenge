package br.com.challenge.services

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class PasswordServiceTest {

    @Inject
    lateinit var passwordService: PasswordService

    @Test
    fun `should run successfully with a valid password`() {
        assertThat("Password test is valid!").isEqualTo(passwordService.validatePassword("test"))
    }
}
