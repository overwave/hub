package dev.overwave.chess.dto

import dev.overwave.chess.model.SessionStatus

data class SimpleSessionResponseDto (
    val id: Long,
    val status: SessionStatus
)
