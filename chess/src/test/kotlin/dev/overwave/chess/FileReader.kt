package dev.overwave.chess

import org.springframework.core.io.ClassPathResource

fun readText(path: String): String = ClassPathResource(path).file.readText()
