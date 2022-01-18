DROP TABLE IF EXISTS post;

CREATE TABLE IF NOT EXISTS post (
    id SERIAL PRIMARY KEY,
    name TEXT,
    created DATE,
    description TEXT
);

DROP TABLE IF EXISTS candidate;

CREATE TABLE IF NOT EXISTS candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    created DATE,
    id_city integer
);

DROP TABLE IF EXISTS cities;

CREATE TABLE IF NOT EXISTS cities (
    id SERIAL PRIMARY KEY,
    name TEXT
);

INSERT INTO cities(name) values ('Мск'),('Спб'),('Екб');