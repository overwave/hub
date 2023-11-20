package dev.overwave.chess.core

class Initializer {

    private val greeting = "Hello, let's play chess!"
    private val board = Array(8) { Array(8) { "" } }
    private val blackFigures = arrayOf("♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜")


    fun fillBoardLikeAPro() {
        for ((y, row) in board.withIndex()) {
            for (x in row.indices) {
                board[y][x] = if ((x + y) % 2 == 0) "██" else "  "
            }
        }
    }

    fun printChessBoardLikePro() {
        println(greeting)
        println(ChessBoardParts.FRAME_TOP.view)
        for ((y, row) in board.withIndex()) {
            print("║")
            print(row.joinToString(separator = ""))
            print("║ ")
            println(8 - y)
        }
        println(ChessBoardParts.FRAME_BOTTOM.view)
        println(ChessBoardParts.ALPHABETICAL_INDEXES.view)

    }
}