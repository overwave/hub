package dev.overwave.chess.dto

import dev.overwave.chess.model.SessionStatus

class SessionResponseDto (
    val id: Long,
    val white: String,
    val black: String,
    val status: SessionStatus
) {

}
