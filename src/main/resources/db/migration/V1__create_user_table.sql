CREATE TABLE users(
    id UUID NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    password VARCHAR,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
);