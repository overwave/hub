package dev.overwave.chess.dto

import dev.overwave.chess.service.FigureColor
import dev.overwave.chess.service.PieceType

data class FigureDto(
    val color: FigureColor,
    val type: PieceType,
)
