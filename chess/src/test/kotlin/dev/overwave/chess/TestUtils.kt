package dev.overwave.chess

import org.springframework.test.json.JsonCompareMode
import org.springframework.test.web.servlet.result.ContentResultMatchersDsl

fun ContentResultMatchersDsl.compareJson(path: String) {
    json(readFile(path), compareMode = JsonCompareMode.STRICT)
}
