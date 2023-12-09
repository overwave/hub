--liquibase formatted sql

--changeset author:lizunya

    CREATE TABLE IF NOT EXISTS session_message (
      id BIGSERIAL PRIMARY KEY,
      game_session_id BIGINT NOT NULL REFERENCES game_session(id),
      user_id BIGINT NOT NULL REFERENCES user_(id),
      payload TEXT NOT NULL
    );