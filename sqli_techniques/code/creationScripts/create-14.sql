DROP DATABASE chal14DB;
CREATE DATABASE chal14DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'chal14'@'localhost' IDENTIFIED BY 'chal14';

GRANT SELECT ON chal14DB.* TO 'chal14'@'localhost';

CREATE TABLE chal14DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);

CREATE TABLE chal14DB.timeyFlag (
	flag varchar(255) NOT NULL
);
INSERT INTO chal14DB.timeyFlag VALUES ('FLAG-t!m3y_w!m3y_w!bb1y_w0bb1y_5tuff');

