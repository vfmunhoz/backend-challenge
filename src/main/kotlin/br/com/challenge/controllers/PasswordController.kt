package br.com.challenge.controllers

import br.com.challenge.controllers.entities.PasswordValidationErrorResponse
import br.com.challenge.controllers.entities.PasswordValidationRequest
import br.com.challenge.controllers.entities.PasswordValidationResponse
import br.com.challenge.extensions.toPasswordResponse
import br.com.challenge.services.PasswordService
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces

@Controller("/")
class PasswordController(
        private val passwordService: PasswordService
) {

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun validatePassword(passwordValidationRequest: PasswordValidationRequest): HttpResponse<PasswordValidationResponse> =
        runCatching { passwordService.validatePassword(passwordValidationRequest.password).toPasswordResponse() }
        .getOrThrow()
        .let { passwordValidationResponse ->
            if(passwordValidationResponse.isValid) HttpResponse.ok(passwordValidationResponse)
            else HttpResponse.badRequest(passwordValidationResponse)
        }

    @Error
    fun unexpectedError(request: HttpRequest<*>, throwable: Throwable): HttpResponse<PasswordValidationErrorResponse> =
        HttpResponse.serverError(PasswordValidationErrorResponse("unexpected error handling validations [${throwable.message}]"))
}
