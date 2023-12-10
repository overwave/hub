package dev.overwave.chess.security

import dev.overwave.chess.model.User
import dev.overwave.chess.service.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findByLogin(login) ?: throw UsernameNotFoundException("User not found")
        return UserDto(user.login, user.password, listOf())
    }

    fun checkUserExists(login: String): CheckUserDto {
        val exists = userRepository.findByLogin(login) != null
        return CheckUserDto(exists)
    }

    fun registerUser(login: String, password: String, ipAddress: String) {
        if (userRepository.findByLogin(login) != null) throw UserExistsException(login)
        userRepository.save(
            User(
                login = login,
                password = passwordEncoder.encode(password),
                name = "Anonymous",
                ip = ipAddress,
                bot = false
            )
        )
    }
}