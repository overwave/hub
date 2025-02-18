package dev.overwave.chess.controller

import dev.overwave.chess.dto.BoardResponseDto
import dev.overwave.chess.dto.CreatedLobbyResponseDto
import dev.overwave.chess.dto.LobbyRequestDto
import dev.overwave.chess.dto.OpenLobbiesListDto
import dev.overwave.chess.dto.PlayableSessionResponseDto
import dev.overwave.chess.dto.SimpleSessionResponseDto
import dev.overwave.chess.service.GameService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController("oldGameController")
@RequestMapping(path = ["/chess/api/game"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GameController(
    private val gameService: GameService,
) {
    @GetMapping("/board")
    fun getBoard(principal: Principal?): BoardResponseDto = gameService.getBoard()

    @GetMapping("/open")
    fun getOpenLobbies(): OpenLobbiesListDto = gameService.getOpenLobbies()

    @PostMapping("/start")
    fun startGame(
        principal: Principal,
        @RequestBody request: LobbyRequestDto,
    ): CreatedLobbyResponseDto = gameService.startGame(principal.name, request)

    @PostMapping("/{id}/join")
    fun joinSession(
        principal: Principal,
        @PathVariable id: Long,
    ): SimpleSessionResponseDto = gameService.joinSession(principal.name, id)

    @GetMapping("/{id}")
    fun getSession(
        @PathVariable id: Long,
    ): PlayableSessionResponseDto = gameService.getSession(id)
}
