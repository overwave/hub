package dev.overwave.chess.controller

import dev.overwave.chess.misc.UnitTest
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.model.User
import dev.overwave.chess.repository.SessionRepository
import dev.overwave.chess.repository.UserRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@UnitTest
@WebMvcTest(GameController::class)
class GameControllerTest {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var sessionRepository: SessionRepository
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `when get open sessions then open sessions returned`() {
        val user = userRepository.save(User("login", "name", "ip", "password", false))
        val session = sessionRepository.save(Session(user, null, SessionStatus.OPEN))
        mockMvc.get("/chess/api/game/open").andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("{}", true) }
        }
    }
}