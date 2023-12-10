--liquibase formatted sql

--changeset author:lizunya

    CREATE TABLE IF NOT EXISTS session_history (
      id BIGSERIAL PRIMARY KEY,
      session_id BIGINT NOT NULL REFERENCES session(id),
      figure_id BIGINT NOT NULL REFERENCES figure(id),
      file TEXT NOT NULL,
      rank INT NOT NULL,
      type TEXT NOT NULL,
      taken BOOLEAN NOT NULL,
      created_at TIMESTAMPTZ NOT NULL,
      updated_at TIMESTAMPTZ NOT NULL
    );