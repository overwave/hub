--liquibase formatted sql

--changeset author:lizunya

    CREATE TABLE IF NOT EXISTS user_ (
      id BIGSERIAL PRIMARY KEY,
      ip TEXT NOT NULL,
      name TEXT NOT NULL,
      login TEXT NOT NULL,
      password TEXT NOT NULL,
      bot BOOLEAN NOT NULL,
      CONSTRAINT unique_ip UNIQUE(ip)
    );