package br.com.challenge

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("br.com.challenge")
                .mainClass(Application.javaClass)
                .start()
    }
}
