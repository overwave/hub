--liquibase formatted sql

--changeset author:lizunya

    CREATE TABLE IF NOT EXISTS session_history (
      id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
      session_id BIGINT NOT NULL,
      figure_id BIGINT NOT NULL,
      file VARCHAR(50) NOT NULL,
      rank BIGINT NOT NULL,
      type VARCHAR(50) NOT NULL,
      taken BOOLEAN NOT NULL,
      CONSTRAINT pk_session_history PRIMARY KEY (id),
      CONSTRAINT fk_history_to_session FOREIGN KEY(session_id) REFERENCES session(id),
      CONSTRAINT fk_history_to_figure FOREIGN KEY(figure_id) REFERENCES figure(id)
    );