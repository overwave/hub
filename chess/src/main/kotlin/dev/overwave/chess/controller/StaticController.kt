package dev.overwave.chess.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class StaticController {
    @RequestMapping("/chess/**")
    fun serveStatic(request: HttpServletRequest): String {
        val path = request.servletPath.substringAfter("/chess")
        println(path)
        if (path.contains('.')) {
            return path
        }
        return if (path.isEmpty()) "index.html" else path + ".html"
    }
}