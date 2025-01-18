--liquibase formatted sql

--changeset overwave:piece_type_and_side_types
CREATE TYPE piece_type AS ENUM ('PAWN', 'KNIGHT', 'BISHOP', 'ROOK', 'QUEEN', 'KING', 'STONE',
    'GOLD_GENERAL', 'SILVER_GENERAL', 'LANCE');
CREATE TYPE side AS ENUM ('WHITE', 'BLACK');

--changeset overwave:piece_table
CREATE TABLE IF NOT EXISTS piece
(
    id         BIGSERIAL PRIMARY KEY,
    type       piece_type  NOT NULL,
    side       side        NOT NULL,
    position   TEXT        NOT NULL,
    taken      BOOLEAN     NOT NULL,
    promoted   BOOLEAN     NOT NULL,
    game_id    BIGINT      NOT NULL REFERENCES game (id),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);