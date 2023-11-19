package dev.overwave.hub.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class HubController {
    @GetMapping
    fun get(): Map<String, String> {
        return mapOf("Hello" to "world")
    }
}