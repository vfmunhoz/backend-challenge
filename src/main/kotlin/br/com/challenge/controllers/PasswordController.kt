package br.com.challenge.controllers

import br.com.challenge.services.PasswordService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@Controller("/")
class PasswordController(
        private val passwordService: PasswordService
) {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    fun validatePassword(): String = passwordService.validatePassword("test")
}
