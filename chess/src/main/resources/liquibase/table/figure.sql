--liquibase formatted sql

--changeset author:lizunya

    CREATE TABLE IF NOT EXISTS figure (
      id BIGSERIAL PRIMARY KEY,
      type TEXT NOT NULL,
      color TEXT NOT NULL,
      file TEXT NOT NULL,
      rank BIGINT NOT NULL,
      taken BOOLEAN NOT NULL,
      game_session_id BIGINT NOT NULL REFERENCES game_session(id)
    );