--liquibase formatted sql

--changeset author:overwave
create table spring_session
(
    primary_id            text primary key,
    session_id            text unique not null,
    creation_time         bigint      not null,
    last_access_time      bigint      not null,
    max_inactive_interval int         not null,
    expiry_time           bigint      not null,
    principal_name        text
);

create index spring_session_principal_name_idx on spring_session (principal_name);
create index spring_session_expiry_time_idx on spring_session (expiry_time);
