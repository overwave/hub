package dev.overwave.chess.controller

import dev.overwave.chess.dto.UserDto
import dev.overwave.chess.security.CheckUserDto
import dev.overwave.chess.security.RegisterUserRequestDto
import dev.overwave.chess.security.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping(path = ["/chess/api/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(
    private val userService: UserService
) {
    @GetMapping("/check")
    fun checkUserExists(@RequestParam login: String): CheckUserDto {
        return userService.checkUserExists(login)
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody requestDto: RegisterUserRequestDto, request: HttpServletRequest) {
        userService.registerUser(requestDto.login, requestDto.password, request.remoteAddr)
    }

    @GetMapping("/me")
    fun selfInfo(principal: Principal): UserDto {
        return userService.selfInfo(principal.name)
    }
}