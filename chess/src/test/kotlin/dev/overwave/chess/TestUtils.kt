package dev.overwave.chess

import org.springframework.test.json.JsonCompareMode
import org.springframework.test.web.servlet.result.ContentResultMatchersDsl

fun ContentResultMatchersDsl.compareJson(path: String, vararg format: Pair<String, Any>, mode: JsonCompareMode = JsonCompareMode.STRICT) {
    val json = format.fold(readFile(path)) { acc, (name, value) -> acc.replace("\"{$name}\"", value.toString()) }
    json(json, compareMode = mode)
}
