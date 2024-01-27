package dev.overwave.chess

import org.springframework.core.io.ClassPathResource

fun readFile(path: String) = ClassPathResource(path).file.readText()
