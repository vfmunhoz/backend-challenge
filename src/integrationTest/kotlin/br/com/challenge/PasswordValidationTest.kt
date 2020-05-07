package br.com.challenge

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNullOrEmpty
import assertk.assertions.isTrue
import br.com.challenge.entities.PasswordValidationRequest
import br.com.challenge.entities.PasswordValidationResponse
import br.com.challenge.extensions.ApplicationExtension
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.RegisterExtension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PasswordValidationTest {

    @JvmField
    @RegisterExtension
    val application = ApplicationExtension()

    @Test
    fun `return a 200 htpp status response calling password validation with a valid password`() {
        val request = HttpRequest.POST(PASSWORD_VALIDATION_ENDPOINT, PasswordValidationRequest(VALID_PASSWORD))
        val body = application.client().toBlocking().exchange(request, Argument.of(PasswordValidationResponse::class.java))

        assertThat(body.status).isEqualTo(HttpStatus.OK)
        assertThat(body.body()).isNotNull().given { passwordResponse ->
            assertThat(passwordResponse.isValid).isTrue()
            assertThat(passwordResponse.validationErrors).isNullOrEmpty()
        }
    }

    companion object {

        private const val PASSWORD_VALIDATION_ENDPOINT = "/"
        private const val VALID_PASSWORD = "AbTp9!foo"
    }
}
