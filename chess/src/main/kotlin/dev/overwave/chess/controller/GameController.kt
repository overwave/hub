package dev.overwave.chess.controller

import dev.overwave.chess.dto.BoardResponseDto
import dev.overwave.chess.dto.GameSessionRequestDto
import dev.overwave.chess.dto.GameSessionResponseDto
import dev.overwave.chess.service.GameService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/chess/api/game"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GameController(
    private val gameService: GameService
) {

    @GetMapping("/board")
    fun getBoard(): BoardResponseDto {
        return gameService.getBoard()
    }

    @GetMapping("/open")
    fun getOpenSessions(): List<GameSessionResponseDto> {
        return gameService.getOpenSessions()
    }

    @PostMapping("/start")
    fun startGame(@RequestParam id: Int, @RequestBody session: GameSessionRequestDto): GameSessionResponseDto {
        return gameService.startGame(id, session)
    }
}