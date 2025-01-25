package dev.overwave.chess.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.overwave.chess.compareJson
import dev.overwave.chess.game.chess.dto.ChessGame
import dev.overwave.chess.game.chess.dto.TurnRequest
import dev.overwave.chess.game.core.GameRepository
import dev.overwave.chess.game.core.GameStatus
import dev.overwave.chess.game.dto.toPosition
import dev.overwave.chess.misc.FunctionalTest
import dev.overwave.chess.misc.TestUserFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.json.JsonCompareMode
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
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
                content { compareJson("/game/v2/start/response.json", mode = JsonCompareMode.LENIENT) }
            }

        val game = gameRepository.findAll().single()
        assertThat(game.status).isEqualTo(GameStatus.WHITES_TURN)
    }

    @Test
    fun `make a turn`() {
        userFactory.createUser("user")
        val response =
            mockMvc
                .put("/chess/api/game/v2/start") {
                    content = mapper.writeValueAsString(object {})
                    contentType = MediaType.APPLICATION_JSON
                    with(csrf())
                }.andMapResult<ChessGame>()
        mockMvc
            .put("/chess/api/game/v2/turn") {
                content =
                    mapper.writeValueAsString(
                        TurnRequest(
                            gameId = response.gameId,
                            from = "d2".toPosition(),
                            to = "d4".toPosition(),
                        ),
                    )
                contentType = MediaType.APPLICATION_JSON
                with(csrf())
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { compareJson("/game/v2/turn/response.json", "gameId" to response.gameId) }
            }
    }

    private inline fun <reified T> ResultActionsDsl.andMapResult(): T = mapper.readValue(andReturn().response.contentAsString)
}
