--liquibase formatted sql

--changeset author:lizunya

    CREATE TABLE IF NOT EXISTS session (
      id BIGSERIAL PRIMARY KEY,
      created_at TIMESTAMPTZ NOT NULL,
      updated_at TIMESTAMPTZ NOT NULL,
      white_player_id BIGINT REFERENCES user_(id),
      black_player_id BIGINT REFERENCES user_(id),
      status TEXT NOT NULL
    );