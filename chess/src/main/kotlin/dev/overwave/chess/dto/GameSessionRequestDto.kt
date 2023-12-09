package dev.overwave.chess.dto

import dev.overwave.chess.service.FigureColor

class GameSessionRequestDto (
    val side: FigureColor,
    val against: Int
) {

}
