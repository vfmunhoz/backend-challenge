package br.com.challenge.controllers

import br.com.challenge.controllers.entities.PasswordRequest
import br.com.challenge.controllers.entities.PasswordResponse
import br.com.challenge.extensions.toPasswordResponse
import br.com.challenge.services.PasswordService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces

@Controller("/")
class PasswordController(
        private val passwordService: PasswordService
) {

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun validatePassword(passwordRequest: PasswordRequest): PasswordResponse =
            passwordService.validatePassword(passwordRequest.password).toPasswordResponse()
}
