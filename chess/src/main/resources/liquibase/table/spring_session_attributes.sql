--liquibase formatted sql

--changeset overwave:spring_session_attributes_create_table
create table if not exists spring_session_attributes
(
    session_primary_id text  not null references spring_session (primary_id) on delete cascade,
    attribute_name     text  not null,
    attribute_bytes    bytea not null,
    primary key (session_primary_id, attribute_name)
);
