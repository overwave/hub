package dev.overwave.chess.configuration

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.addSerializer
import dev.overwave.chess.game.dto.Position
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfiguration {
    @Bean
    fun customModule(): Module = SimpleModule().addSerializer(Position::class, PositionSerializer())
}
