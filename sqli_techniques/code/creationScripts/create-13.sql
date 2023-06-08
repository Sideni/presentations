DROP DATABASE chal13DB;
CREATE DATABASE chal13DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER 'chal13'@'localhost' IDENTIFIED BY 'chal13';

GRANT SELECT ON chal13DB.* TO 'chal13'@'localhost';

CREATE TABLE chal13DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);

CREATE TABLE chal13DB.suchFlagMuchTime (
	flag varchar(255) NOT NULL
);
INSERT INTO chal13DB.suchFlagMuchTime VALUES ('FLAG-I_C0u1D_M4k3_4_V3ry_10NG_Fl4G...V3ry_V3ry_V3ry_V3Ry_L0nG:tr0llf4ce:');

