package dev.overwave.chess.dto

import dev.overwave.chess.service.FigureColor

class SessionRequestDto (
    val side: FigureColor,
    val against: Int
) {

}
