package br.com.challenge.controllers

import br.com.challenge.entities.PasswordValidationErrorResponse
import br.com.challenge.entities.PasswordValidationRequest
import br.com.challenge.entities.PasswordValidationResponse
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
import io.micronaut.web.router.exceptions.UnsatisfiedRouteException
import org.slf4j.LoggerFactory

@Controller("/")
class PasswordController(
    private val passwordService: PasswordService
) {

    private val logger = LoggerFactory.getLogger(PasswordController::class.java)

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun validatePassword(passwordValidationRequest: PasswordValidationRequest): HttpResponse<PasswordValidationResponse> =
        passwordService.validatePassword(passwordValidationRequest.password).toPasswordResponse().let { passwordValidationResponse ->
            logger.info("request to validate password done. password is valid? [${passwordValidationResponse.isValid}] details [${passwordValidationResponse.validationErrors}]")

            if(passwordValidationResponse.isValid) HttpResponse.ok(passwordValidationResponse)
            else HttpResponse.badRequest(passwordValidationResponse)
        }

    @Error
    fun parserException(parse: UnsatisfiedRouteException): HttpResponse<PasswordValidationErrorResponse> =
        HttpResponse.badRequest(PasswordValidationErrorResponse("was impossible to serialize the payload [${parse.message}]")).also {
            logger.error("error parsing password validation payload", parse)
        }
}
