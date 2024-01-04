package dev.overwave.chess.controller

import dev.overwave.chess.dto.BoardResponseDto
import dev.overwave.chess.dto.SessionResponseDto
import dev.overwave.chess.dto.StartSessionRequestDto
import dev.overwave.chess.service.GameService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping(path = ["/chess/api/game"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GameController(
    private val gameService: GameService
) {

    @GetMapping("/board")
    fun getBoard(principal: Principal?): BoardResponseDto {
        return gameService.getBoard()
    }

    @GetMapping("/open")
    fun getOpenSessions(): List<SessionResponseDto> {
        return gameService.getOpenSessions()
    }

    @PostMapping("/start")
    fun startGame(@RequestParam playerId: Int, @RequestBody request: StartSessionRequestDto): SessionResponseDto {
        return gameService.startGame(playerId, request)
    }
}