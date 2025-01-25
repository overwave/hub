package dev.overwave.chess.configuration

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.TextNode
import dev.overwave.chess.game.dto.Position
import dev.overwave.chess.game.dto.toPosition

class PositionDeserializer : JsonDeserializer<Position>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Position =
        p.codec
            .readTree<TextNode>(p)
            .asText()
            .toPosition()
}
