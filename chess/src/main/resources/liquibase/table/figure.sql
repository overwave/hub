--liquibase formatted sql

--changeset author:lizunya runOnChange:true

    CREATE TABLE IF NOT EXISTS figure (
      id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
      type VARCHAR(50) NOT NULL,
      color VARCHAR(50) NOT NULL,
      address VARCHAR(50) NOT NULL,
      session_id BIGINT NOT NULL,
      CONSTRAINT pk_figure PRIMARY KEY (id),
      CONSTRAINT fk_figure_to_session FOREIGN KEY(session_id) REFERENCES session(id),
    );