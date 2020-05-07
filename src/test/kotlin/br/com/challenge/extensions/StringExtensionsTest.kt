package br.com.challenge.extensions

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StringExtensionsTest {

    @ParameterizedTest
    @ValueSource(strings = ["Test", "tEst", "tesT"])
    fun `given a string containing upper and lower case characters the output must be true`(value: String) {
        assertThat(value.hasLowerAndUpperCaseLetters()).isTrue()
    }

    @Test
    fun `given a string which doesn'' contain upper and lower case characters the output must be false`() {
        assertThat("test".hasLowerAndUpperCaseLetters()).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["1test", "te1st", "test1"])
    fun `given a string containing digits the output must be true`(value: String) {
        assertThat(value.hasDigits()).isTrue()
    }

    @Test
    fun `given a string which doesn't contain digits the output must be false`() {
        assertThat("test".hasDigits()).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["!test", "te@st", "test#"])
    fun `given a string containing special characters the output must be true`(value: String) {
        assertThat(value.hasSpecialChars()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["test", "test1"])
    fun `given a string which doesn't contain special characters the output must be false`(value: String) {
        assertThat(value.hasSpecialChars()).isFalse()
    }
}
