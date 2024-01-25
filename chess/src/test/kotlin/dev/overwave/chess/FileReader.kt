package dev.overwave.chess

fun readText(path: String): String {
    return object {}.javaClass.getResource(path)?.readText()
        ?: throw IllegalArgumentException("Не удалось найти файл по адресу $path")
}