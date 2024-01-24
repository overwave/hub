--liquibase formatted sql

--changeset author:lizunya session_message_create_table
CREATE TABLE IF NOT EXISTS session_message
(
    id         BIGSERIAL PRIMARY KEY,
    session_id BIGINT      NOT NULL REFERENCES session (id),
    user_id    BIGINT      NOT NULL REFERENCES user_ (id),
    payload    TEXT        NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);