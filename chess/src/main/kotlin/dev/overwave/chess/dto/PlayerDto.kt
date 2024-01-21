package dev.overwave.chess.dto

data class PlayerDto(
    val name: String,
    val login: String,
    val bot: Boolean = false,
)
