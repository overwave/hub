package dev.overwave.chess.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Profile("!test")
@Configuration
class SecurityConfiguration(
    private val objectMapper: ObjectMapper
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
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests {
            it.anyRequest().permitAll()
        }.httpBasic(withDefaults()).cors {
        }.formLogin {
            it.loginPage("/chess/login")
            it.loginProcessingUrl("/chess/api/user/login")
            it.successHandler { _, response, _ ->
                response.writer.write(objectMapper.writeValueAsString(LoginDto(LoginStatus.SUCCESS)))
            }
            it.failureHandler { _, response, _ ->
                response.writer.write(objectMapper.writeValueAsString(LoginDto(LoginStatus.FAILED)))
            }
            it.permitAll()
        }.logout {
            it.logoutUrl("/chess/logout")
            it.deleteCookies("JSESSIONID")
            it.permitAll()
        }.csrf {
            it.disable() // TODO enable
        }.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}