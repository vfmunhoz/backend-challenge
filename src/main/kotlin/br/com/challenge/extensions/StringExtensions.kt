package br.com.challenge.extensions

private const val PASSWORD_PATTERN_REGEX = "((?=.*[A-Z])(?=.*[a-z])).*"
private const val STRING_CONTAINS_DIGITS_REGEX = "(?=.*\\d).*"
private const val PASSWORD_HAS_SPECIAL_CHARS_REGEX = "(?=.*[\\W]).*"

fun String.hasLowerAndUpperCaseLetters(): Boolean = PASSWORD_PATTERN_REGEX.toRegex().matches(this)

fun String.hasDigits(): Boolean = STRING_CONTAINS_DIGITS_REGEX.toRegex().matches(this)

fun String.hasSpecialChars(): Boolean = PASSWORD_HAS_SPECIAL_CHARS_REGEX.toRegex().matches(this)
