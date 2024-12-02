package dev.overwave.chess.controller

import com.fasterxml.jackson.databind.ObjectMapper
import dev.overwave.chess.dto.StartSessionRequestDto
import dev.overwave.chess.misc.FunctionalTest
import dev.overwave.chess.misc.TestUserFactory
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.readFile
import dev.overwave.chess.readText
import dev.overwave.chess.repository.SessionRepository
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
    private val userFactory: TestUserFactory,
    private val sessionRepository: SessionRepository,
    private val mockMvc: MockMvc,
    private val mapper: ObjectMapper,
) {
    @Test
    @WithMockUser
    fun `when get open sessions then open sessions returned`() {
        val white = userFactory.createUser()
        val black = userFactory.createUser()
        val bot = userFactory.createUser(bot = true)
        val whiteSession = sessionRepository.save(Session(white, null, SessionStatus.OPEN))
        val blackSession = sessionRepository.save(Session(null, black, SessionStatus.OPEN))
        val botSession = sessionRepository.save(Session(null, bot, SessionStatus.OPEN))
        mockMvc.get("/chess/api/game/open").andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(readFile("/game/open/response.json")) }
        }
    }

    @Test
    @WithMockUser
    fun `when start session with player then open session created`() {
        userFactory.createUser("user")
        val sessionRequestWhite = StartSessionRequestDto(FigureColor.WHITE, Opponent.PLAYER)

        mockMvc
            .post("/chess/api/game/start") {
                content = mapper.writeValueAsString(sessionRequestWhite)
                contentType = MediaType.APPLICATION_JSON
                with(csrf())
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(readFile("/game/start/response_with_player.json")) }
            }
    }

    @Test
    @WithMockUser
    fun `when start session with bot then session in progress created`() {
        userFactory.createUser("user")
        userFactory.createUser(bot = true)
        val sessionRequestWithBot = StartSessionRequestDto(FigureColor.BLACK, Opponent.BOT)

        mockMvc
            .post("/chess/api/game/start") {
                content = mapper.writeValueAsString(sessionRequestWithBot)
                contentType = MediaType.APPLICATION_JSON
                with(csrf())
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(readFile("/game/start/response_with_bot.json")) }
            }
    }

    @Test
    @WithMockUser
    fun `when join session then return session in progress`() {
        val white = userFactory.createUser("user")
        val whiteSession = sessionRepository.save(Session(white, null, SessionStatus.OPEN))
        val black = userFactory.createUser("user1")

        mockMvc
            .post("/chess/api/game/{id}/join", whiteSession.id) {
                with(csrf())
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(readText("/game/start/response_with_bot.json")) }
            }
    }
}
