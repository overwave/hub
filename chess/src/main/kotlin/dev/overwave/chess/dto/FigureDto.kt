package dev.overwave.chess.dto

import dev.overwave.chess.service.FigureColor
import dev.overwave.chess.service.FigureType

data class FigureDto (
    val color: FigureColor,
    val type: FigureType,
)
