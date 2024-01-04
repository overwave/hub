package dev.overwave.chess.dto

import dev.overwave.chess.service.FigureColor

class StartSessionRequestDto (
    val side: FigureColor,
    val against: Long?
) {

}
