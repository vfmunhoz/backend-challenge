package br.com.challenge.services

import javax.inject.Singleton

@Singleton
class PasswordService {

    fun validatePassword(password: String): Pair<Boolean, String> = true to "Password $password is valid!"
}
