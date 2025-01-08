package dev.overwave.chess.game.chess

data class Position(
    val x: Int,
    val y: Int,
) {
    val file = 'a'.plus(if (x > 8) x - 2 else x - 1)
    val rank = y.toString()

    override fun toString(): String = "$file$rank"
}

fun String.toPosition(): Position {
    var x = 0
    var y = 0
    for (char in this) {
        if (char in '0'..'9') {
            y = y * 10 + char.code - '0'.code
        } else if (char in 'a'..'t' && char != 'i') {
            x = x * 10 + char.code - 'a'.code + 1
            if (char.code > 'i'.code) x--
        } else {
            throw IllegalArgumentException(this)
        }
    }
    return Position(x = x, y = y)
}
