package dev.overwave.chess.dto

import dev.overwave.chess.model.LobbySide

data class OpenLobbyDto(
    val id: Long,
    val opponent: PlayerDto,
    val opponentSide: LobbySide,
)
