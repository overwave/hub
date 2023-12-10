--liquibase formatted sql

--changeset author:lizunya

    CREATE TABLE IF NOT EXISTS user_ (
      id BIGSERIAL PRIMARY KEY,
      ip TEXT NOT NULL UNIQUE,
      name TEXT NOT NULL,
      login TEXT NOT NULL,
      password TEXT NOT NULL,
      bot BOOLEAN NOT NULL,
      created_at TIMESTAMPTZ NOT NULL,
      updated_at TIMESTAMPTZ NOT NULL
    );