package dev.overwave.chess.security

import dev.overwave.chess.service.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findByLogin(login) ?: throw UsernameNotFoundException("User not found")
        return UserDto(user.login, user.password, listOf())
    }

    fun checkUser(login: String): CheckUserDto {
        val exists = userRepository.findByLogin(login) != null
        return CheckUserDto(exists)
    }
}