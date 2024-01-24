--liquibase formatted sql

--changeset author:lizunya_user_create_table
CREATE TABLE IF NOT EXISTS user_
(
    id         BIGSERIAL PRIMARY KEY,
    ip         TEXT        NOT NULL UNIQUE,
    name       TEXT        NOT NULL,
    login      TEXT        NOT NULL,
    password   TEXT        NOT NULL,
    bot        BOOLEAN     NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

--changeset author:overwave user_drop_ip
ALTER TABLE user_
    DROP COLUMN IF EXISTS ip;
