package dev.overwave.chess.controller

import dev.overwave.chess.service.BoardResponseDto
import dev.overwave.chess.service.GameService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/chess/api/game"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GameController(
    private val gameService : GameService
) {

    @GetMapping("/board")
    fun getBoard() : BoardResponseDto {
        return gameService.getBoard()
    }
}