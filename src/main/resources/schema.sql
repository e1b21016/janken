CREATE TABLE users (
    id IDENTITY PRIMARY KEY,
    userName VARCHAR NOT NULL
);

CREATE TABLE matches (
    id IDENTITY PRIMARY KEY,
    user1 INT,
    user2 INT,
    user1Hand VARCHAR NOT NULL,
    user2Hand VARCHAR NOT NULL,
    isActive boolean NOT NULL
);

CREATE TABLE matchinfo(
  id IDENTITY PRIMARY KEY,
  user1 INT,
  user2 INT,
  user1Hand VARCHAR NOT NULL,
  isActive boolean NOT NULL
);
