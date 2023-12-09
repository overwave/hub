--liquibase formatted sql

--changeset author:lizunya

    CREATE TABLE IF NOT EXISTS session_history (
      id BIGSERIAL PRIMARY KEY,
      game_session_id BIGINT NOT NULL REFERENCES game_session(id),
      figure_id BIGINT NOT NULL REFERENCES figure(id),
      file TEXT NOT NULL,
      rank BIGINT NOT NULL,
      type TEXT NOT NULL,
      taken BOOLEAN NOT NULL
    );