package dev.overwave.chess.core

class Initializer {

    private val greeting = "Hello, let's play chess!"
    private val board = Array(8) { Array(8) {""} }
    private val blackFigures = arrayOf("♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜")

    fun printChessBoardLikeLizunya() {
        println(greeting)
        println(ChessBoardParts.ALPHABETICAL_INDEXES.view)
        println(ChessBoardParts.FRAME_TOP.view)
        for(i in 8 downTo 2 step 2) {
            println("${ChessBoardParts.EMPTY_CELLS_1.view} $i")
            println("${ChessBoardParts.EMPTY_CELLS_2.view} ${i - 1}")
        }
        println(ChessBoardParts.FRAME_BOTTOM.view)
        println(ChessBoardParts.ALPHABETICAL_INDEXES.view)
    }

    fun printChessBoardLikePro() {
        for ((index, row) in board.withIndex()) {
            if(index % 2 == 0) {
                if(index == 7) {
                    for (i in row.indices) {

                    }


                }
                else {
                    for (i in row.indices) {
                        if(i % 2 == 0) {
                            row[i] = "  "
                        } else
                            row[i] ="██"
                    }
                }
            } else {
                for (i in row.indices) {
                    if (i % 2 == 0) {
                        row[i] = "██"
                    } else
                        row[i] = "  "
                }
            }
        }

        val boardAsString = board.mapIndexed { index, row ->
            val cells = row.joinToString("  ")
            "║$cells║ ${index + 1}"
        }

        println(greeting)
        println(ChessBoardParts.FRAME_TOP.view)

        // 2️⃣
        println(boardAsString.reversed().joinToString("\n"))
        println(ChessBoardParts.FRAME_BOTTOM.view)
        println(ChessBoardParts.ALPHABETICAL_INDEXES.view)

    }


}