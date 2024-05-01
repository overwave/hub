--liquibase formatted sql

--changeset author:lizunya session_create_table
CREATE TABLE IF NOT EXISTS lobby
(
    id              BIGSERIAL   PRIMARY KEY,
    user_id         BIGINT      REFERENCES user_ (id),
    status          TEXT        NOT NULL,
    side            TEXT        NOT NULL,
    created_at      TIMESTAMPTZ NOT NULL,
    updated_at      TIMESTAMPTZ NOT NULL
);