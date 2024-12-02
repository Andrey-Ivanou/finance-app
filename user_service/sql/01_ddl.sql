CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.users
(
    id character varying NOT NULL,
    mail character varying NOT NULL,
    full_name character varying NOT NULL,
    role character varying NOT NULL,
    status character varying NOT NULL,
    password character varying NOT NULL,
    create_at timestamp without time zone NOT NULL,
    update_at timestamp without time zone NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;


CREATE TABLE app.users
(
    id character varying NOT NULL,
    mail character varying NOT NULL,
    full_name character varying NOT NULL,
    role character varying NOT NULL,
    status character varying varying NOT NULL,
    password character varying NOT NULL,
    create_at timestamp without time zone NOT NULL,
    update_at timestamp without time zone NOT NULL,
    code_user bigint,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;


CREATE TABLE app.codes_users
(
    code bigint,
    PRIMARY KEY (code)
);

ALTER TABLE IF EXISTS app.codes_users
    OWNER to postgres;


CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.users
(
    id character varying NOT NULL,
    mail character varying NOT NULL,
    full_name character varying NOT NULL,
    role smallint NOT NULL,
    status smallint NOT NULL,
    password character varying NOT NULL,
    create_at bigserial NOT NULL,
    update_at bigserial NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;