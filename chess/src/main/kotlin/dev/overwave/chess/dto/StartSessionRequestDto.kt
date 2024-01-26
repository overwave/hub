package dev.overwave.chess.dto

import dev.overwave.chess.service.FigureColor
import dev.overwave.chess.service.Opponent

data class StartSessionRequestDto (
    val side: FigureColor,
    val opponent: Opponent
)
