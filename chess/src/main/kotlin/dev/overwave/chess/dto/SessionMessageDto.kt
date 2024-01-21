package dev.overwave.chess.dto

data class SessionMessageDto(
    val sessionId: Long,
    val login: String,
    val payload: String
)