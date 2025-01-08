package dev.overwave.chess.controller

import com.fasterxml.jackson.databind.ObjectMapper
import dev.overwave.chess.misc.FunctionalTest
import dev.overwave.chess.readFile
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.put

@FunctionalTest
class GameV2ControllerTest(
    private val mockMvc: MockMvc,
    private val mapper: ObjectMapper,
) {
    @Test
    fun `base start game test`() {
        mockMvc
            .put("/chess/api/game/v2/start") {
                content = mapper.writeValueAsString(object {})
                contentType = MediaType.APPLICATION_JSON
                with(csrf())
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(readFile("/game/v2/start/response_with_player.json"), strict = true) }
            }
    }
}
