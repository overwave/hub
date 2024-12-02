package dev.overwave.chess.dto

import dev.overwave.chess.model.LobbySide
import dev.overwave.chess.service.Opponent

data class LobbyRequestDto(
    val side: LobbySide,
    val opponent: Opponent,
)
