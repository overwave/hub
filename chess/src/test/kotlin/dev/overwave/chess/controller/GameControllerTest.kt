package dev.overwave.chess.controller

import dev.overwave.chess.misc.FunctionalTest
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.model.User
import dev.overwave.chess.repository.SessionRepository
import dev.overwave.chess.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@FunctionalTest
class GameControllerTest(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val mockMvc: MockMvc,
) {
    @Test
    @WithMockUser
    fun `when get open sessions then open sessions returned`() {
        val user = userRepository.save(User("login", "name", "ip", "password", false))
        val session = sessionRepository.save(Session(user, null, SessionStatus.OPEN))
        mockMvc.get("/chess/api/game/open").andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("[{\"id\":1,\"status\":\"OPEN\"}]", true) }
        }
    }
}