package dev.overwave.chess.controller

import dev.overwave.chess.dto.BoardResponseDto
import dev.overwave.chess.dto.PlayableSessionResponseDto
import dev.overwave.chess.dto.PlayerDto
import dev.overwave.chess.dto.SessionRequestDto
import dev.overwave.chess.dto.SessionRequestListDto
import dev.overwave.chess.dto.SimpleSessionResponseDto
import dev.overwave.chess.dto.StartSessionRequestDto
import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.service.FigureColor
import dev.overwave.chess.service.GameService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
    fun getOpenSessions(): SessionRequestListDto {
        return SessionRequestListDto(
            listOf(
                SessionRequestDto(123, PlayerDto("Родион", "overwave"), FigureColor.BLACK, SessionStatus.OPEN),
                SessionRequestDto(124, PlayerDto("Лизавета", "arhideya"), FigureColor.WHITE, SessionStatus.OPEN),
                SessionRequestDto(125, PlayerDto("Афина", "athene", bot = true), FigureColor.WHITE, SessionStatus.OPEN),
            )
        )
    }

    @PostMapping("/start")
    fun startGame(principal: Principal, @RequestBody request: StartSessionRequestDto): SimpleSessionResponseDto {
        return gameService.startGame(principal.name, request)
    }

    @PostMapping("/{id}/join")
    fun joinSession(principal: Principal, @PathVariable id: Long): SimpleSessionResponseDto {
        return gameService.joinSession(principal.name, id)
    }

    @GetMapping("/{id}")
    fun getSession(@PathVariable id: Long): PlayableSessionResponseDto {
        return gameService.getSession(id)
    }
}