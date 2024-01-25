package dev.overwave.chess.controller

import com.fasterxml.jackson.databind.ObjectMapper
import dev.overwave.chess.dto.StartSessionRequestDto
import dev.overwave.chess.misc.FunctionalTest
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.model.User
import dev.overwave.chess.readText
import dev.overwave.chess.repository.SessionRepository
import dev.overwave.chess.repository.UserRepository
import dev.overwave.chess.service.FigureColor
import dev.overwave.chess.service.Opponent
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@FunctionalTest
class GameControllerTest(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val mockMvc: MockMvc,
    private val mapper: ObjectMapper,
) {

    @Test
    @WithMockUser
    fun `when get open sessions then open sessions returned`() {
        val white = userRepository.save(User("login1", "name1", "ip1", "password1", false))
        val black = userRepository.save(User("login2", "name2", "ip2", "password2", false))
        val bot = userRepository.save(User("hephaestus", "Hephaestus", "ip3", "password3", true))
        val whiteSession = sessionRepository.save(Session(white, null, SessionStatus.OPEN))
        val blackSession = sessionRepository.save(Session(null, black, SessionStatus.OPEN))
        val botSession = sessionRepository.save(Session(null, bot, SessionStatus.OPEN))
        mockMvc.get("/chess/api/game/open").andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(readText("/game/open/response.json")) }
        }
    }

    @Test
    @WithMockUser
    fun `when start session with player then open session created`() {
        val sessionRequestWhite = StartSessionRequestDto(FigureColor.WHITE, Opponent.PLAYER)

        mockMvc.post("/chess/api/game/start") {
            content = mapper.writeValueAsString(sessionRequestWhite)
            contentType = MediaType.APPLICATION_JSON
            with(csrf())
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(readText("/game/start/response_with_player.json")) }
        }
    }

    @Test
    @WithMockUser
    fun `when start session with bot then session in progress created`() {
        val black = userRepository.save(User("user", "name2", "ip2", "password2", false))
        val bot = userRepository.save(User("hephaestus", "Hephaestus", "ip3", "password3", true))
        val sessionRequestWithBot = StartSessionRequestDto(FigureColor.BLACK, Opponent.BOT)

        mockMvc.post("/chess/api/game/start") {
            content = mapper.writeValueAsString(sessionRequestWithBot)
            contentType = MediaType.APPLICATION_JSON
            with(csrf())
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(readText("/game/start/response_with_bot.json")) }
        }
    }
}