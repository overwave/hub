package dev.overwave.chess

import java.io.FileNotFoundException

fun readText(path: String): String {
    return object {}.javaClass.getResource(path)?.readText()
        ?: throw FileNotFoundException("Не удалось найти файл по адресу $path")
}