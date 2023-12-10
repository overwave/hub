package dev.overwave.chess.misc

import io.mockk.mockk
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.crypto.password.PasswordEncoder

@TestConfiguration
@ComponentScan("dev.overwave.chess")
class TestConfiguration {
    @Bean
    fun passwordEncoder() = mockk<PasswordEncoder>()
}