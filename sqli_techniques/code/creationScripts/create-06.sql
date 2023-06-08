CREATE USER chal06 WITH PASSWORD 'chal06';

DROP DATABASE chal06DB;
CREATE DATABASE chal06DB WITH OWNER chal06 ENCODING 'UTF8';

\c chal06db;

DROP SCHEMA chal06DB;
CREATE SCHEMA chal06DB AUTHORIZATION chal06;

ALTER ROLE chal06 SET search_path=chal06db,public;
GRANT SELECT ON ALL TABLES IN SCHEMA chal06DB TO chal06;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA chal06DB TO chal06;

SET ROLE chal06;

SET search_path TO chal06DB,public;

CREATE TABLE chal06DB.users (
	username varchar(40) NOT NULL PRIMARY KEY,
	password varchar(40) NOT NULL
);

CREATE TABLE chal06DB.flagitoPorLaCarne (
	flag varchar(255) NOT NULL
);
INSERT INTO chal06DB.flagitoPorLaCarne VALUES ('FLAG-5h0w_z3_3rr0rs_2_L3T_p33ps_kn0W_i7_41NT_w0rkING');
