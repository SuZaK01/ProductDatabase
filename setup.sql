CREATE TABLE products (
  EAN  BIGSERIAL PRIMARY KEY UNIQUE,
  name TEXT NOT NULL,
  desc TEXT NOT NULL
);

INSERT INTO products (EAN, name, descr) VALUES (5010255079763, 'Paperclips Large', 'Large Plain Pack of 1000');
INSERT INTO products (EAN, name, descr) VALUES (5018206244666, 'Giant Paperclips', 'Giant Plain 51mm 100 pack');
INSERT INTO products (EAN, name, descr) VALUES (5018306332812, 'Paperclip Giant Plain', 'Giant Plain Pack of 10000');
INSERT INTO products (EAN, name, descr)
VALUES (5018306312913, 'No Tear Paper Clip', 'No Tear Extra Large Pack of 1000');
INSERT INTO products (EAN, name, descr)
VALUES (5018206244611, 'Zebra Paperclips', 'Zebra Length 28mm Assorted 150 Pack');

COMMIT;


CREATE TABLE artists (
  id   BIGSERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE albums (
  artist_id BIGINT NOT NULL,
  title     TEXT   NOT NULL,
  year      INT,
  rating    INT,
  id        BIGSERIAL PRIMARY KEY
);

INSERT INTO albums (artist, title) VALUES ('Keyboard cat', 'Keyboard Cat''s Greatest hits');
INSERT INTO albums (artist, title) VALUES ('Spice girls', 'Spice');
INSERT INTO albums (artist, title) VALUES ('Rick Atley', 'Whenever you need somebody');
INSERT INTO albums (artist, title) VALUES ('Manowar', 'The triumph of steel');
INSERT INTO albums (artist, title) VALUES ('Justin Biber', 'Believe');

WITH cat AS (
  INSERT INTO artists (name) VALUES ('Keyboard Cat')
  RETURNING id
) INSERT INTO albums (artist_id, title, year, rating) VALUES
  ((SELECT cat.id
    FROM cat), 'Keyboard Cat''s Greatests Hits', 2010, 4),
  ((SELECT cat.id
    FROM cat), 'Suns shine fast', 2012, 5);

CREATE TABLE customers (
  id           BIGSERIAL PRIMARY KEY,
  first_name   TEXT NOT NULL,
  last_name    TEXT NOT NULL,
  properties1  TEXT,
  properties2  TEXT,
  properties3  TEXT,
  properties4  TEXT,
  properties5  TEXT,
  properties6  TEXT,
  properties7  TEXT,
  properties8  TEXT,
  properties9  TEXT,
  properties10 TEXT,
  properties11 TEXT,
  properties12 TEXT,
  properties13 TEXT,
  properties14 TEXT,
  properties15 TEXT,
  properties16 TEXT,
  properties17 TEXT,
  properties18 TEXT
);

CREATE TABLE addresses(
  id BIGSERIAL PRIMARY KEY,
  customer_id BIGINT NOT NULL,
  street text,
  house_nr text,
  apartament_nr TEXT,
  zip text,
  city text,
  country text
);

CREATE TABLE users(
  id BIGSERIAL PRIMARY KEY ,
  email TEXT NOT NULL ,
  first_name TEXT,
  last_name TEXT
);