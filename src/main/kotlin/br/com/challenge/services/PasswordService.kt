package br.com.challenge.services

import javax.inject.Singleton

@Singleton
class PasswordService {

    fun validatePassword(password: String) = "Password $password is valid!"
}
