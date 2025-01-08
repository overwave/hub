package dev.overwave.chess.game.chess

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/chess/api/game/v2"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GameController(
    private val gameService: GameService,
) {
    @PutMapping("/start")
    fun startGame(): Any = gameService.startGame()
}
