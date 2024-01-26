package dev.overwave.chess.dto

data class OpenSessionDto(
    val id: Long,
    val opponent: PlayerDto,
    val opponentSide: Side,
)
