package dev.overwave.chess.service

import dev.overwave.chess.misc.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@UnitTest
class GameServiceTest(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val gameService: GameService,
) {
    @BeforeEach
    fun setUp() {
        // since liquibase is currently broken :(
        jdbcTemplate.update(
            """
                create table session
                (
                    id              bigserial,
                    black_player_id bigint,
                    white_player_id bigint,
                    status          text,
                    created_at      timestamptz not null,
                    updated_at      timestamptz not null
                );
        """, mapOf<String, Any>()
        )
    }

    @Test
    fun testGameService() {
        val actualBoard = gameService.getBoard()
        val expectedBoard = BoardResponseDto(
            board = mapOf(
                FigureDto(FigureColor.WHITE, FigureType.ROOK) at "a1",
                FigureDto(FigureColor.WHITE, FigureType.KNIGHT) at "b1",
                FigureDto(FigureColor.WHITE, FigureType.BISHOP) at "c1",
                FigureDto(FigureColor.WHITE, FigureType.QUEEN) at "d1",
                FigureDto(FigureColor.WHITE, FigureType.KING) at "e1",
                FigureDto(FigureColor.WHITE, FigureType.BISHOP) at "f1",
                FigureDto(FigureColor.WHITE, FigureType.KNIGHT) at "g1",
                FigureDto(FigureColor.WHITE, FigureType.ROOK) at "h1",

                FigureDto(FigureColor.WHITE, FigureType.PAWN) at "a2",
                FigureDto(FigureColor.WHITE, FigureType.PAWN) at "b2",
                FigureDto(FigureColor.WHITE, FigureType.PAWN) at "c2",
                FigureDto(FigureColor.WHITE, FigureType.PAWN) at "d2",
                FigureDto(FigureColor.WHITE, FigureType.PAWN) at "e2",
                FigureDto(FigureColor.WHITE, FigureType.PAWN) at "f2",
                FigureDto(FigureColor.WHITE, FigureType.PAWN) at "g2",
                FigureDto(FigureColor.WHITE, FigureType.PAWN) at "h2",


                FigureDto(FigureColor.BLACK, FigureType.ROOK) at "a8",
                FigureDto(FigureColor.BLACK, FigureType.KNIGHT) at "b8",
                FigureDto(FigureColor.BLACK, FigureType.BISHOP) at "c8",
                FigureDto(FigureColor.BLACK, FigureType.QUEEN) at "d8",
                FigureDto(FigureColor.BLACK, FigureType.KING) at "e8",
                FigureDto(FigureColor.BLACK, FigureType.BISHOP) at "f8",
                FigureDto(FigureColor.BLACK, FigureType.KNIGHT) at "g8",
                FigureDto(FigureColor.BLACK, FigureType.ROOK) at "h8",

                FigureDto(FigureColor.BLACK, FigureType.PAWN) at "a7",
                FigureDto(FigureColor.BLACK, FigureType.PAWN) at "b7",
                FigureDto(FigureColor.BLACK, FigureType.PAWN) at "c7",
                FigureDto(FigureColor.BLACK, FigureType.PAWN) at "d7",
                FigureDto(FigureColor.BLACK, FigureType.PAWN) at "e7",
                FigureDto(FigureColor.BLACK, FigureType.PAWN) at "f7",
                FigureDto(FigureColor.BLACK, FigureType.PAWN) at "g7",
                FigureDto(FigureColor.BLACK, FigureType.PAWN) at "h7",
            )
        )
        assertThat(actualBoard).isEqualTo(expectedBoard)
    }

    private infix fun FigureDto.at(address: String) = address to TileDto(address, this)
}