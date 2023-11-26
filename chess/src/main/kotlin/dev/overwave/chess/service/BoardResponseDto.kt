package dev.overwave.chess.service

data class BoardResponseDto(
    val board: Map<String, TileDto>
)
