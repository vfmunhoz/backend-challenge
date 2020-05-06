package br.com.challenge.services

import br.com.challenge.entities.PasswordStatus
import javax.inject.Singleton

@Singleton
class PasswordService {

    fun validatePassword(password: String): PasswordStatus {
        return validateLength(password)
    }

    fun validateLength(password: String) =
            if(password.length >= PASSWORD_MINIMUM_SIZE) {
                true to "Password $password is valid!"
            } else {
                false to "Password has less than 9 characters"
            }

    companion object {
        private const val PASSWORD_MINIMUM_SIZE = 9
    }
}
