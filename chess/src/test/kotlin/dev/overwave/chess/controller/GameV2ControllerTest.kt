package dev.overwave.chess.controller

import com.fasterxml.jackson.databind.ObjectMapper
import dev.overwave.chess.compareJson
import dev.overwave.chess.game.core.GameRepository
import dev.overwave.chess.game.core.GameStatus
import dev.overwave.chess.misc.FunctionalTest
import dev.overwave.chess.misc.TestUserFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.put

@FunctionalTest
class GameV2ControllerTest(
    private val gameRepository: GameRepository,
    private val userFactory: TestUserFactory,
    private val mockMvc: MockMvc,
    private val mapper: ObjectMapper,
) {
    @Test
    fun `start game returns filled board`() {
        userFactory.createUser("user")
        mockMvc
            .put("/chess/api/game/v2/start") {
                content = mapper.writeValueAsString(object {})
                contentType = MediaType.APPLICATION_JSON
                with(csrf())
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { compareJson("/game/v2/start/response_with_player.json") }
            }

        val game = gameRepository.findAll().single()
        assertThat(game.status).isEqualTo(GameStatus.WHITES_TURN)
    }
}
