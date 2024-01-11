package dev.overwave.chess.dto

import dev.overwave.chess.model.SessionStatus

data class SimpleSessionResponseDto (
    val id: Long,
    // val whiteSideLogin: String?,
    // val blackSideLogin: String?,
    val status: SessionStatus
) {

}
