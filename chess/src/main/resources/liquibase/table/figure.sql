--liquibase formatted sql

--changeset lizunya:figure_create_table
CREATE TABLE IF NOT EXISTS figure
(
    id         BIGSERIAL PRIMARY KEY,
    type       TEXT        NOT NULL,
    side       TEXT        NOT NULL,
    file       TEXT        NOT NULL,
    rank       INT         NOT NULL,
    taken      BOOLEAN     NOT NULL,
    session_id BIGINT      NOT NULL REFERENCES session (id),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);