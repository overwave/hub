--liquibase formatted sql

--changeset author:lizunya session_create_table
CREATE TABLE IF NOT EXISTS session
(
    id              BIGSERIAL PRIMARY KEY,
    created_at      TIMESTAMPTZ NOT NULL,
    updated_at      TIMESTAMPTZ NOT NULL,
    white_player_id BIGINT REFERENCES user_ (id),
    black_player_id BIGINT REFERENCES user_ (id),
    status          TEXT        NOT NULL
);

--changeset author:lizunya players_id_not_null
ALTER TABLE session
    ALTER COLUMN white_player_id SET NOT NULL
    ALTER COLUMN black_player_id SET NOT NULL;
