package br.com.challenge

import assertk.assertThat
import assertk.assertions.isNotNull
import br.com.challenge.entities.PasswordValidationRequest
import br.com.challenge.extensions.ApplicationExtension
import io.micronaut.http.HttpRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.RegisterExtension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PasswordValidationTest {

    @JvmField
    @RegisterExtension
    val application = ApplicationExtension()

    @Test
    fun `return a 200 htpp status response calling a password validation with a valid password`() {
        val request = HttpRequest.POST("/", PasswordValidationRequest("AbTp9!foo"))
        val body = application.client().toBlocking().retrieve(request)

        assertThat(body).isNotNull()
    }
}
