package dev.overwave.chess.game.dto

data class Position(
    val x: Int,
    val y: Int,
) {
    init {
        require(x in 1..19)
        require(y in 1..19)
    }

    val file = 'a'.plus(if (x > 8) x - 2 else x - 1)
    val rank = y.toString()

    override fun toString(): String = "$file$rank"
}

fun String.toPosition(): Position {
    if (this.length !in 2..3) throw IllegalArgumentException("Invalid input string $this")
    val x =
        when (val fileChar = this[0]) {
            in 'a'..'h' -> fileChar - 'a' + 1
            in 'j'..'t' -> fileChar - 'a' // 'j' starts from 9 as 'i' is skipped
            else -> throw IllegalArgumentException("Invalid input string $this")
        }

    val y = this.substring(1).toIntOrNull()
    if (y !in 1..19) throw IllegalArgumentException("Invalid input string $this")
    return Position(x = x, y = y!!)
}
