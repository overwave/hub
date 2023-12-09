package dev.overwave.chess.controller

import dev.overwave.chess.security.CheckUserDto
import dev.overwave.chess.security.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/chess/api/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(
    private val userService : UserService
) {
    @GetMapping("/check")
    fun getBoard(@RequestParam login: String) : CheckUserDto {
        return userService.checkUser(login)
    }
}