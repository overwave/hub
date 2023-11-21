package dev.overwave.chess.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/chess/api"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ApiController {
    @GetMapping
    fun test(): String {
        return "Hello world"
    }
}