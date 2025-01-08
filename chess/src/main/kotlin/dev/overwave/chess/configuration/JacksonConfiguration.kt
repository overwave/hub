package dev.overwave.chess.configuration

import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.addSerializer
import dev.overwave.chess.game.chess.Position
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfiguration {
    @Bean
    fun customModule(): com.fasterxml.jackson.databind.Module = SimpleModule().addSerializer(Position::class, PositionSerializer())
}
