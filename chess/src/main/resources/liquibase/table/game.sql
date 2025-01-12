--liquibase formatted sql

--changeset overwave:game_status_type
CREATE TYPE game_status AS ENUM ('WHITES_TURN','BLACKS_TURN','FINISHED','CANCELLED');

--changeset overwave:game_table
CREATE TABLE IF NOT EXISTS game
(
    id              BIGSERIAL PRIMARY KEY,
    created_at      TIMESTAMPTZ NOT NULL,
    updated_at      TIMESTAMPTZ NOT NULL,
    finished_at     TIMESTAMPTZ,
    white_player_id BIGINT REFERENCES user_ (id),
    black_player_id BIGINT REFERENCES user_ (id),
    status          game_status NOT NULL
);
