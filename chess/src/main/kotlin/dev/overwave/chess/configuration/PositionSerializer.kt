package dev.overwave.chess.configuration

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import dev.overwave.chess.game.chess.Position

class PositionSerializer : JsonSerializer<Position>() {
    override fun serialize(value: Position, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.toString())
    }
}
