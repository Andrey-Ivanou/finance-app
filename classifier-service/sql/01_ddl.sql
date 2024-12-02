CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.currency
(
    id character varying NOT NULL,
    title character varying NOT NULL,
    description character varying NOT NULL,
    create_at timestamp without time zone NOT NULL,
    update_at timestamp without time zone NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.currency
    OWNER to postgres;


CREATE TABLE app.category
(
    id character varying NOT NULL,
    title character varying NOT NULL,
    create_at timestamp without time zone NOT NULL,
    update_at timestamp without time zone NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.category
    OWNER to postgres;