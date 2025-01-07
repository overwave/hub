package dev.overwave.chess.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import dev.overwave.chess.security.LoginDto
import dev.overwave.chess.security.LoginStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.concurrent.TimeUnit

@Configuration
class SecurityConfiguration(
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8081", "https://overwave.dev")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/chess/api/user/me", "/chess/api/game/start", "/chess/api/game/{id}/join")
                    .authenticated()
                it.anyRequest().permitAll()
            }.formLogin {
                it.loginPage("/chess/login")
                it.loginProcessingUrl("/chess/api/user/login")
                it.successHandler { _, response, _ ->
                    response.writer.write(objectMapper.writeValueAsString(LoginDto(LoginStatus.SUCCESS)))
                }
                it.failureHandler { _, response, _ ->
                    response.writer.write(objectMapper.writeValueAsString(LoginDto(LoginStatus.FAILED)))
                    response.status = 403
                }
                it.permitAll()
            }.cors {
            }.logout {
                it.logoutUrl("/chess/api/user/logout")
                it.deleteCookies("JSESSIONID")
                it.permitAll()
            }.csrf {
                it.disable() // TODO enable
            }.rememberMe {
                it.rememberMeCookieName("logged_id")
                it.tokenValiditySeconds(TimeUnit.DAYS.toSeconds(30).toInt())
                it.useSecureCookie(true)
                it.key("secret")
            }.build()

    @Bean
    @Profile("!test")
    fun passwordEncoder() = BCryptPasswordEncoder()
}
