CREATE USER 'hibernate_user'@'localhost' IDENTIFIED BY 'You_thought_it_was_hibernate_password?';

DROP DATABASE IF EXISTS hibernate_DB;
CREATE DATABASE hibernate_DB CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

GRANT SELECT, INSERT, UPDATE, CREATE ON hibernate_DB.* TO 'hibernate_user'@'localhost';

CREATE TABLE hibernate_DB.User (
  id bigint(20) NOT NULL,
  creationTime bigint(20) DEFAULT NULL,
  email varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  name varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  password varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  role varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  salt varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  username varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO hibernate_DB.User VALUES
    (1, 123456789, 'admin@localhost', 'admin', 'kawaboognga!!!', 'admin', 'salt', 'admin');

CREATE TABLE hibernate_DB.YouDidntGetHereWithHQLi (
    id BIGINT(20),
    flag VARCHAR(256)
);

INSERT INTO hibernate_DB.YouDidntGetHereWithHQLi VALUES
    (1, "FLAG-ThisIsSuchASurprise!");

